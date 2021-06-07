package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo adVo){

        PageInfo pageInfo = promotionAdService.findAllAdByPage(adVo);

        ResponseResult result = new ResponseResult(true, 200, "分页查询成功", pageInfo);

        return result;
    }

    /*
        图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");

        // D:\apache-tomcat-8.5.56\webapps\
        String webappPath = realPath.substring(0,realPath.indexOf("ssm_web"));

        //3.获取原文件名  156186.jnp
        String filename = file.getOriginalFilename();

        //4.拼接唯一的新文件名，防止重名   164164813155 + .jpg
        String newfilename = System.currentTimeMillis() + filename.substring(filename.indexOf("."));

        //5.上传文件
        String uploadPath = webappPath + "upload\\";
        File filePath = new File(uploadPath,newfilename);

        //如果不存在upload目录，则创建目录
        if(!filePath.getParentFile().exists()){
            filePath.mkdir();
        }
        //上传图片
        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newfilename);
        map.put("filePath","http://localhost:8080/upload/" + newfilename);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);

        return result;
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "新增广告信息成功", null);
            return result;
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "修改广告信息成功", null);
            return result;
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        ResponseResult result = new ResponseResult(true, 200, "回显广告信息成功", promotionAd);
        return result;
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        ResponseResult result = new ResponseResult(true,200,"修改广告状态成功",null);
        return result;
    }
}
