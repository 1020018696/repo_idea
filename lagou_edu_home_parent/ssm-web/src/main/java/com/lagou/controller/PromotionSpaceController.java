package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();

        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);

        return result;
    }

    /*
    添加广告位\修改广告位
    */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId()==null){
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "新增广告位成功", null);
            return result;
        }else{
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "修改广告位成功", null);
            return result;
        }
    };

    /*
        根据广告位ID回显广告位信息
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult result = new ResponseResult(true, 200, "回显广告位成功", promotionSpace);

        return result;
    }

}
