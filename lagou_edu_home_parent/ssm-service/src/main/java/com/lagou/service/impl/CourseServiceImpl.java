package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        return courseList;
    }

    /*
        保存课程信息和讲师信息
     */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {

        Course course = new Course();
        //借助beanutils封装Course对象
        BeanUtils.copyProperties(courseVo,course);

        //补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //course.setIsDel(0);

        //保存课程信息，同时返回课程id并封装到course中
        courseMapper.saveCourse(course);

        int id = course.getId();

        //封装Teacher对象
        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(courseVo,teacher);

        //补全信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);

        //保存课程信息
        courseMapper.saveTeacher(teacher);

    }

    /*
        根据课程id查询课程信息
     */
    @Override
    public ResponseResult findCourseById(Integer id) {
        CourseVo courseVo = courseMapper.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVo);
        return result;
    }

    /**
     * 修改课程信息
     * */
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        Course course = new Course();

        BeanUtils.copyProperties(courseVo,course);

        Date date = new Date();
        course.setUpdateTime(date);

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(courseVo,teacher);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());

        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }


}
