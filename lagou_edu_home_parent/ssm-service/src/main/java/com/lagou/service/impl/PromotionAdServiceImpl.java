package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVo adVo) {

        //开启分页，在查询语句中会加入limit
        PageHelper.startPage(adVo.getCurrentPage(),adVo.getPageSize());
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);
        return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        //补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        //补全信息
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {

        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setUpdateTime(new Date());
        promotionAd.setStatus(status);

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
