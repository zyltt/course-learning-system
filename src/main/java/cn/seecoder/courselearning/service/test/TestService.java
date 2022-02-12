package cn.seecoder.courselearning.service.test;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import cn.seecoder.courselearning.vo.test.StudentAnsVO;
import cn.seecoder.courselearning.vo.test.StudentAttendTestVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TestService {
    //查询当前课程所有测试
    List<TestVO> getCourseTests(Integer cid);
    //老师获得测试信息
    TestVO teacherGetTest(Integer cid,Integer tid);
    //老师创建测试
    ResultVO<TestVO> teacherCreateTest(TestVO test);
    //老师更新测试
    ResultVO<TestVO> teacherUpdateTest(TestVO test);
    //老师删除测试
    ResultVO<TestVO> teacherDeleteTest(Integer tid);
    //老师往测试中插入题目
    ResultVO<TestVO> InsertProblemIntoTest(Integer pid, Integer tid);
    //老师从测试中移除题目
    ResultVO<TestVO> RemoveProblemFromTest(Integer pid, Integer tid);
    //学生提交测试
    ResultVO<TestVO> studentSubmitTest(List<StudentAnsVO> studentAnsVOList);
    //触发器
    ResultVO<TestVO> trigger(Integer tid);
    //学生查看某测试信息
    ResultVO<StudentAttendTestVO> studentGetTest(Integer tid,Integer uid);
}
