package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {

    /*
        分页获取广告信息
     */
    public PageInfo findAllAdByPage(PromotionAdVo adVo);

    /*
        新增广告信息
     */
    void savePromotionAd(PromotionAd promotionAd);
    /*
        修改广告信息
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * */
    PromotionAd findPromotionAdById(int id);

    /*
        更新广告状态
     */
    void updatePromotionAdStatus(int id, int status);

}
