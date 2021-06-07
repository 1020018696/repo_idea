package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /*
        根据课程id查询课程内容（章节+课时）
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",list);

        return responseResult;
    }

    /*
        根据课程id回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }

    /*
        新增或修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){

        if(section.getId() == null){
            courseContentService.saveSection(section);

            ResponseResult result = new ResponseResult(true, 200, "保存章节信息成功", null);

            return result;
        }else{

            courseContentService.updateSection(section);

            ResponseResult result = new ResponseResult(true, 200, "修改章节信息成功", null);

            return result;
        }


    }

    /*
        修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改状态成功", map);

        return result;
    }

    /*
        新增课时
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson){

        courseContentService.saveLesson(lesson);

        ResponseResult result = new ResponseResult(true, 200, "保存课时信息成功", null);

        return result;
    }

}
