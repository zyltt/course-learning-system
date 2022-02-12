package cn.seecoder.courselearning.mapperservice.test;

import cn.seecoder.courselearning.po.test.TestAttend;
import org.apache.ibatis.annotations.Param;

public interface TestAttendMapper {
    int deleteByTestId(@Param("testId")Integer tid);
    int insert(TestAttend record);
    TestAttend selectByPrimaryKey(Integer tid,Integer uid);
    int CountByPrimaryKey(Integer tid, Integer uid);
}
