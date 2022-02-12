package cn.seecoder.courselearning.mapperservice.test;
import cn.seecoder.courselearning.po.test.StudentAns;

public interface StudentAnsMapper {
    int insert(StudentAns record);
    int delete(StudentAns record);
    int deleteByProblemId(Integer pid);
    StudentAns selectByPrimaryKey(Integer uid,Integer pid,Integer tid);
    int deleteByTestId(Integer tid);
}
