package cn.seecoder.courselearning.check;

import cn.seecoder.courselearning.controller.TestController;
import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.po.test.Test;
import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.serviceimpl.test.TestServiceImpl;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.TestVO;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCheck {
    @Resource
    private TestServiceImpl testServiceImpl;

    public void teacherCreateTestCheck() throws ParseException {
        SimpleDateFormat pt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=pt.parse("2021-08-02 12:12:12");
        Date finishdate=pt.parse("2021-08-02 14:12:12");
        TestVO testVO = new TestVO();
        testVO.setFinishtime(finishdate);
        testVO.setStarttime(startdate);
        testVO.setTestname("421nb");
        testVO.setCid(1);
        testVO.setTid(1);
        testVO.setState(Test_State.NO_START);
        testVO.setTotal_marks(0);
        ResultVO<TestVO> ret = testServiceImpl.teacherCreateTest(testVO);
        System.out.println(ret.getCode());
        System.out.println(ret.getMsg());
        System.out.println(ret.getData().getCid());
        System.out.println(ret.getData().getState());
    }
    public TestCheck(TestServiceImpl TestServiceImplest){
        testServiceImpl=TestServiceImplest;

    }
}
