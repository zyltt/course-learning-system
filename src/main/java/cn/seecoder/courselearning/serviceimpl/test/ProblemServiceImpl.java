package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.mapperservice.test.ProblemMapper;
import cn.seecoder.courselearning.mapperservice.test.SelectProblemMapper;
import cn.seecoder.courselearning.mapperservice.test.StudentAnsMapper;
import cn.seecoder.courselearning.mapperservice.test.TestMapper;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.test.Problem;
import cn.seecoder.courselearning.po.test.StudentAns;
import cn.seecoder.courselearning.po.test.Test;
import cn.seecoder.courselearning.service.test.ProblemService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProblemServiceImpl implements ProblemService {
    @Resource
    private ProblemMapper problemMapper;
    @Resource
    private SelectProblemMapper selectProblemMapper;
    @Resource
    private StudentAnsMapper studentAnsMapper;
    @Resource
    private TestMapper testMapper;
    @Override
    public List<ProblemVO> getCourseProblems(Integer cid) {
        List<ProblemVO> ret = new ArrayList<>();
        List<Problem> problemList = problemMapper.selectByCourseId(cid);
        for (Problem problem : problemList) {
            ret.add(new ProblemVO(problem));
        }
        return ret;
    }
    @Override
    public ResultVO<ProblemVO> teacherCreateProblem(ProblemVO problemVO){
        Problem problem = new Problem(problemVO);
        if (problem.getDescribe().isEmpty())
            return new ResultVO<>(Constant.REQUEST_FAIL,"题干不能为空");
        if (problem.getAnswer().isEmpty())
            return new ResultVO<>(Constant.REQUEST_FAIL,"答案不能为空");
        if (problem.getValue()<0)
            return new ResultVO<>(Constant.REQUEST_FAIL,"分值不能为负数");
        if (!problemMapper.selectByCourseId(problem.getCid()).isEmpty()) {
            for (Problem prob : problemMapper.selectByCourseId(problem.getCid())) {
                if (prob.getDescribe().equals(problem.getDescribe()) && prob.getAnswer().equals(problem.getAnswer()))
                    return new ResultVO<>(Constant.REQUEST_FAIL, "已存在相同题目");
            }
        }
        if(problemMapper.insert(problem) > 0){
            return new ResultVO<ProblemVO>(Constant.REQUEST_SUCCESS, "题目创建成功", new ProblemVO(problem));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    //TODO 问问大龙要干嘛
    public ResultVO<ProblemVO> teacherDeleteProblem(Integer pid){
        if(pid<0)
            return new ResultVO<>(Constant.REQUEST_FAIL, "删除失败，非法题目id");
        Integer cid = problemMapper.selectByPrimaryKey(pid).getCid();
        for (Test test:testMapper.selectByCourseId(cid))
            if (test.getState().equals(Test_State.AVAILABLE))
                return new ResultVO<>(Constant.REQUEST_FAIL, "相关测试运行中，删除失败");
        if (problemMapper.deleteByPrimaryKey(pid)>0) {
            selectProblemMapper.deleteByProblemId(pid);
            problemMapper.deleteByPrimaryKey(pid);
            studentAnsMapper.deleteByProblemId(pid);
            return new ResultVO<ProblemVO>(Constant.REQUEST_SUCCESS, "题目删除成功");
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "删除失败");
    }

    @Override
    public ResultVO<ProblemVO> teacherUpdateProblem(ProblemVO problemVO){
        Problem problem = new Problem(problemVO);
        Integer pid = problem.getPid();
        Integer cid = problemMapper.selectByPrimaryKey(pid).getCid();
        if (problem.getDescribe().isEmpty())
            return new ResultVO<>(Constant.REQUEST_FAIL,"更新失败，题干不能为空");
        if (problem.getAnswer().isEmpty())
            return new ResultVO<>(Constant.REQUEST_FAIL,"更新失败，答案不能为空");
        if (problem.getValue()<0)
            return new ResultVO<>(Constant.REQUEST_FAIL,"更新失败，分值不能为负数");
        for(Problem prob: problemMapper.selectByCourseId(problem.getCid())) {
            if (prob.getDescribe().equals(problem.getDescribe()) && prob.getAnswer().equals(problem.getAnswer()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "更新失败，已存在相同题目");
        }
        if (problemMapper.updateByPrimaryKey(problem)>0){
            return new ResultVO<ProblemVO>(Constant.REQUEST_SUCCESS,"更新成功");
        }
        return new ResultVO<>(Constant.REQUEST_FAIL,"服务器错误");
    }
    @Override
    public ProblemVO teacherGetProblem(Integer cid,Integer pid){
        Problem problem=problemMapper.selectByPrimaryKey(pid);
        return new ProblemVO(problem);
    }
    @Override
    public List<ProblemVO> teacherGetProblemsByTestId(Integer cid, Integer tid) {
        List<Integer> pids = selectProblemMapper.selectByTestId(tid);
        List<ProblemVO> ret = new ArrayList<>();
        for (Integer pid : pids) {
            ret.add(new ProblemVO(problemMapper.selectByPrimaryKey(pid)));
        }
        return ret;
    }
}
