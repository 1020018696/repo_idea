package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*
        课程查询&条件查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        List<Course> courseList = courseService.findCourseByCondition(courseVo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);

        return result;
    }

    /*
        图片上传接口
     */

    @RequestMapping("/courseUpload")
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

    /*
        保存或更新课程信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){

        if(courseVo.getId() == null){
            courseService.saveCourseOrTeacher(courseVo);

            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);

            return responseResult;
        }else{
            courseService.updateCourseOrTeacher(courseVo);

            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);

            return responseResult;
        }
    }

    /*
    根据课程id查询课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        ResponseResult responseResult = courseService.findCourseById(id);
        return responseResult;
    }

    /*
        更新课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改课程状态成功", map);
        return responseResult;
    }
}
