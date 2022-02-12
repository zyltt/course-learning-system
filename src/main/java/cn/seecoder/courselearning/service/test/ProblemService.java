package cn.seecoder.courselearning.service.test;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProblemService {
    List<ProblemVO> getCourseProblems(Integer cid);

    ResultVO<ProblemVO> teacherCreateProblem(ProblemVO problemVO);

    ResultVO<ProblemVO> teacherDeleteProblem(Integer pid);

    ResultVO<ProblemVO> teacherUpdateProblem(ProblemVO problemVO);

    ProblemVO teacherGetProblem(Integer cid,Integer pid);
    //老师查询某一测试已选题目
    List<ProblemVO> teacherGetProblemsByTestId(Integer cid, Integer tid);
}
