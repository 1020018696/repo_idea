package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseService {


    //多条件查询
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /*
        保存课程信息和讲师信息
     */
    public void saveCourseOrTeacher(CourseVo courseVo);

    /*
        根据课程id查询课程信息
     */
    public ResponseResult findCourseById(Integer id);

    /**
     * 修改课程信息
     * */
    public void updateCourseOrTeacher(CourseVo courseVo);

    /*
        更新课程状态
     */
    public void updateCourseStatus(int id,int status);

}
