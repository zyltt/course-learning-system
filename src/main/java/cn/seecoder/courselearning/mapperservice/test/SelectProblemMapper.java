package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.test.Problem;
import cn.seecoder.courselearning.po.test.SelectProblem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectProblemMapper {
    int insert(@Param("problemId") Integer problemId, @Param("testId") Integer testId);
    int deleteByPrimaryKey(@Param("problemId") Integer problemId, @Param("testId") Integer testId);
    void deleteByProblemId(Integer problemId);
    void deleteByTestId(Integer testId);
    List<Integer> selectByTestId(Integer testId);
}
