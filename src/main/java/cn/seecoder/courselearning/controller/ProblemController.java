package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.test.ProblemService;
import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Resource
    private ProblemService problemService;
    /**
     * 查询当前课程所有题目
     * @param cid 课程id
     */
    @GetMapping("/cid/{cid}")
    public List<ProblemVO> getCourseProblems(@PathVariable Integer cid){return problemService.getCourseProblems(cid);}
    /**
     * 教师创建题目
     * @param problemVO 问题VO
     */
    @PostMapping("/create")
    public ResultVO<ProblemVO> teacherCreateProblem(@RequestBody ProblemVO problemVO){
        return problemService.teacherCreateProblem(problemVO);
    }
    /**
     * 教师删除题目
     * @param pid 问题id
     */
    @PostMapping("/delete/{pid}")
    public ResultVO<ProblemVO> teacherDeleteProblem(@PathVariable Integer pid){return problemService.teacherDeleteProblem(pid);}
    /**
     * 更新题目
     * @param problemVO 问题VO
     */
    @GetMapping("/update")
    public ResultVO<ProblemVO> teacheUpdateProblem(@RequestBody ProblemVO problemVO){
        return problemService.teacherUpdateProblem(problemVO);
    }
    /**
     * 教师得到某题目信息
     * @param pid 题目id
     */
    @GetMapping("/getProblem")
    public ProblemVO teacherGetProblem(@RequestParam Integer cid, @RequestParam Integer pid){
        return problemService.teacherGetProblem(cid,pid);
    }
    /**
     * 查询当前测试所有已添加的题目
     * @param cid 课程id
     * @param tid 测试id
     */
    @GetMapping("/getTestProblems")
    public List<ProblemVO> teacherGetProblemsByTestId(@RequestParam Integer cid, @RequestParam Integer tid){
        return problemService.teacherGetProblemsByTestId(cid,tid);
    }
}
