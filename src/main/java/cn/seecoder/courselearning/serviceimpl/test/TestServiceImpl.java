package cn.seecoder.courselearning.serviceimpl.test;

import cn.seecoder.courselearning.enums.Ans_State;
import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.mapperservice.test.*;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.test.*;
import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import cn.seecoder.courselearning.vo.test.StudentAnsVO;
import cn.seecoder.courselearning.vo.test.StudentAttendTestVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;
    @Resource
    private SelectProblemMapper selectProblemMapper;
    @Resource
    private TestAttendMapper testAttendMapper;
    @Resource
    private StudentAnsMapper studentAnsMapper;
    @Resource
    private ProblemMapper problemMapper;
    @Override
    public List<TestVO> getCourseTests(Integer cid) {
        List<TestVO> ret = new ArrayList<>();
        List<Test> testList = testMapper.selectByCourseId(cid);
        for (Test test : testList) {
            ret.add(new TestVO(test));
        }
        return ret;
    }
    @Override
    public TestVO teacherGetTest(Integer cid,Integer tid){
        Test test=testMapper.selectByPrimaryKey(tid);
        return new TestVO(test);
    }
    @Override
    public ResultVO<TestVO> teacherCreateTest(TestVO testVO){
        if(testVO.getTestname().isEmpty()){
            return new ResultVO<>(Constant.REQUEST_FAIL, "测试名为空");
        }
        if(!testVO.getStarttime().after(new Date())){
            return new ResultVO<>(Constant.REQUEST_FAIL, "开始时间不合法");
        }
        if(testVO.getFinishtime().before(testVO.getStarttime())){
            return new ResultVO<>(Constant.REQUEST_FAIL, "结束时间不合法");
        }
        if(!testMapper.selectByCourseId(testVO.getCid()).isEmpty()) {
            for (Test test : testMapper.selectByCourseId(testVO.getCid())) {
               if (test.getTestname().equals(testVO.getTestname()))
                    return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名测试！");
            }
        }
        Test test=new Test(testVO);
        test.setState(Test_State.NO_START);
        test.setTotal_marks(0);
        if(testMapper.insert(test) > 0){
            return new ResultVO<TestVO>(Constant.REQUEST_SUCCESS, "测试创建成功。", new TestVO(test));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }

    @Override
    public ResultVO<TestVO> teacherUpdateTest(TestVO testVO) {
        Integer tid = testVO.getTid();
        Integer cid = testMapper.selectByPrimaryKey(tid).getCid();
        if(testVO.getTestname().isEmpty()){
            return new ResultVO<>(Constant.REQUEST_FAIL, "更新失败，测试名为空");
        }
        if(!testVO.getStarttime().after(new Date())){
            return new ResultVO<>(Constant.REQUEST_FAIL, "更新失败，开始时间不合法");
        }
        if(testVO.getFinishtime().before(testVO.getStarttime())){
            return new ResultVO<>(Constant.REQUEST_FAIL, "更新失败，结束时间不合法");
        }
        for(Test test: testMapper.selectByCourseId(testVO.getCid())){
            if (test.getTestname().equals(testVO.getTestname()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名测试！");
        }
        Test test = new Test(testVO);
        test.setCid(cid);
        test.setTotal_marks(0);
        test.setState(Test_State.NO_START);
        if (testMapper.updateByPrimaryKey(test)>0){
            return new ResultVO<TestVO>(Constant.REQUEST_SUCCESS, "更新测试成功。", new TestVO(test));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }
    @Override
    public ResultVO<TestVO> teacherDeleteTest(Integer tid){
        if (testMapper.selectByPrimaryKey(tid).getState().equals(Test_State.AVAILABLE))
            return new ResultVO<>(Constant.REQUEST_FAIL, "更新失败，该测试正在运行");
        Test_State state = testMapper.selectByPrimaryKey(tid).getState();
        if(testMapper.deleteByPrimaryKey(tid)>0) {
            if (!selectProblemMapper.selectByTestId(tid).isEmpty())
                selectProblemMapper.deleteByTestId(tid);
            if (state.equals(Test_State.FINISHED)) {
                testAttendMapper.deleteByTestId(tid);
                studentAnsMapper.deleteByTestId(tid);
            }
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "删除测试成功。");
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "删除失败");
    }

    @Override
    public ResultVO<TestVO> InsertProblemIntoTest(Integer pid,Integer tid){
        if (pid<0)
            return new ResultVO<>(Constant.REQUEST_FAIL, "插入失败，非法题目编号");
        if (tid<0)
            return new ResultVO<>(Constant.REQUEST_FAIL, "插入失败，非法测试编号");
        for (Integer Pid: selectProblemMapper.selectByTestId(tid)) {
            if (pid==Pid)
                return new ResultVO<>(Constant.REQUEST_FAIL, "插入失败，已存在相同题目");
        }
        if (selectProblemMapper.insert(pid,tid)>0)
            return  new ResultVO<>(Constant.REQUEST_SUCCESS,"插入成功");
        return new ResultVO<>(Constant.REQUEST_FAIL, "插入失败");
    }

    @Override
    public ResultVO<TestVO>  RemoveProblemFromTest(Integer pid,Integer tid){
        if (pid<0)
            return new ResultVO<>(Constant.REQUEST_FAIL, "删除失败，非法题目编号");
        if (tid<0)
            return new ResultVO<>(Constant.REQUEST_FAIL, "删除失败，非法测试编号");
        if (selectProblemMapper.deleteByPrimaryKey(pid,tid)>0)
            return  new ResultVO<>(Constant.REQUEST_SUCCESS,"删除成功");
        return new ResultVO<>(Constant.REQUEST_FAIL,"删除失败");
    }
    @Override
    public ResultVO<TestVO> studentSubmitTest(List<StudentAnsVO> studentAnsVOList){
        int Tid = studentAnsVOList.get(0).getTid();
        int Uid = studentAnsVOList.get(0).getUid();
        if (testAttendMapper.CountByPrimaryKey(Tid, Uid) > 0) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "您已提交过答卷，请勿重复提交！");
        }

        int total_marks=0;
        for(StudentAnsVO studentAnsVO:studentAnsVOList){
            StudentAns studentAns=new StudentAns(studentAnsVO);
            Problem thisproblem=problemMapper.selectByPrimaryKey(studentAnsVO.getPid());
            if(thisproblem.getAnswer().equals(studentAns.getAnswer())){
                studentAns.setState(Ans_State.Accepted);
                total_marks+=thisproblem.getValue();
            }
            else{
                studentAns.setState(Ans_State.Wrong_Answer);
            }
            studentAnsMapper.insert(studentAns);
        }
        TestAttend testAttend=new TestAttend();
        testAttend.setTid(studentAnsVOList.get(0).getTid());
        testAttend.setGet_score(total_marks);
        testAttend.setUid(studentAnsVOList.get(0).getUid());
        if (testAttendMapper.insert(testAttend)>0)
            return new ResultVO<>(Constant.REQUEST_SUCCESS,"提交成功");
        return new ResultVO<>(Constant.REQUEST_FAIL,"提交失败");
    }
    @Override
    public ResultVO<TestVO> trigger(Integer tid){
        Date presenttime=new Date();
        Date starttime=testMapper.selectByPrimaryKey(tid).getStarttime();
        Date finishtime=testMapper.selectByPrimaryKey(tid).getFinishtime();
        if(presenttime.after(starttime)&&presenttime.before(finishtime)){
            Test test = testMapper.selectByPrimaryKey(tid);
            test.setState(Test_State.AVAILABLE);
            int total_marks = 0;
            for (Problem problem: problemMapper.selectByTestId(test.getTid()))
                total_marks+=problem.getValue();
            test.setTotal_marks(total_marks);
            testMapper.updateByPrimaryKey(test);
        }
        if(presenttime.after(finishtime)){
            Test test = testMapper.selectByPrimaryKey(tid);
            test.setState(Test_State.FINISHED);
            testMapper.updateByPrimaryKey(test);
        }
        return new ResultVO<TestVO>(Constant.REQUEST_SUCCESS,"查询状态信息成功",new TestVO(testMapper.selectByPrimaryKey(tid)));
    }
    @Override
    public ResultVO<StudentAttendTestVO> studentGetTest(Integer tid,Integer uid){
        TestVO testVO=new TestVO(testMapper.selectByPrimaryKey(tid));
        List<Integer> pids = selectProblemMapper.selectByTestId(tid);
        List<ProblemVO> problems=new ArrayList<>();
        for(Integer pid:pids){
            problems.add(new ProblemVO(problemMapper.selectByPrimaryKey(pid)));
        }
        if (testAttendMapper.CountByPrimaryKey(tid,uid)==0)
            return new ResultVO<>(Constant.REQUEST_FAIL,"您未参加本场考试");
        List<String> answers=new ArrayList<>();
        for(Integer pid:pids){
            answers.add(studentAnsMapper.selectByPrimaryKey(uid,pid,tid).getAnswer());
        }
        int get_marks=testAttendMapper.selectByPrimaryKey(tid,uid).getGet_score();
        List<Integer> studentmarks=new ArrayList<>();
        for(Integer pid:pids){
            if(studentAnsMapper.selectByPrimaryKey(uid,pid,tid).getState()==Ans_State.Accepted){
                studentmarks.add(problemMapper.selectByPrimaryKey(pid).getValue());
            }
            else
                studentmarks.add(0);
        }
        return new ResultVO<StudentAttendTestVO>(Constant.REQUEST_SUCCESS,"",new StudentAttendTestVO(testVO,problems,answers,studentmarks,get_marks));
    }
}
