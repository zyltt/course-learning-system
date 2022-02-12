package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.test.Test;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Test record);
    int updateByPrimaryKey(Test record);
    Test selectByPrimaryKey(Integer id);
    List<Test> selectByCourseId(Integer courseId);
}
