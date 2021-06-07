package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        广告分页查询
     */
    public List<PromotionAd> findAllAdByPage();

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
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
