package cn.seecoder.courselearning.check;

import cn.seecoder.courselearning.controller.CourseController;
import cn.seecoder.courselearning.controller.TestController;
import cn.seecoder.courselearning.serviceimpl.test.TestServiceImpl;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;

import java.text.ParseException;

public class Driver {
    public static void main(String args[]) throws ParseException {
        TestServiceImpl testServiceImpl= new TestServiceImpl();
        TestCheck testCheck = new TestCheck(testServiceImpl);
        CourseController courseController=new CourseController();
        CourseVO ret = courseController.getCourseById(1,1);
        System.out.println(ret.getTeacherName());
    }
}
