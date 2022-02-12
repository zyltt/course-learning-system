package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.test.Problem;
import cn.seecoder.courselearning.po.test.Test;

import java.util.List;

public interface ProblemMapper {
    int insert(Problem record);
    Problem selectByPrimaryKey(Integer id);
    List<Problem> selectByCourseId(Integer courseId);
    List<Problem> selectByTestId(Integer testId);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Problem record);
}
