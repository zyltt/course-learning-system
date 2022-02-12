package cn.seecoder.courselearning.controller;


import cn.seecoder.courselearning.enums.Ans_State;
import cn.seecoder.courselearning.mapperservice.test.SelectProblemMapper;
import cn.seecoder.courselearning.service.test.TestService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.test.ProblemVO;
import cn.seecoder.courselearning.vo.test.StudentAnsVO;
import cn.seecoder.courselearning.vo.test.StudentAttendTestVO;
import cn.seecoder.courselearning.vo.test.TestVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;
    /**
     * 老师/学生查询当前课程所有测试
     * @param cid 课程id
     */
    @GetMapping("/cid/{cid}")
    public List<TestVO> getCourseTests(@PathVariable Integer cid){return testService.getCourseTests(cid);}
    /**
     * 教师获取单一测试
     * @param cid 课程id
     * @param tid 测试id
     */
    @GetMapping("/{tid}")
    public TestVO teacherGetTest(@RequestParam(required = false, defaultValue ="" ) Integer cid, @PathVariable Integer tid){
        return  testService.teacherGetTest(cid, tid);
    }
    /**
     * 教师创建测试
     * @param  test 测试
     */
    @PostMapping("/create")
    public ResultVO<TestVO> teacherCreateTest(@RequestBody TestVO test) {
        return testService.teacherCreateTest(test);
    }
    /**
     * 教师删除测试
     * @param  tid 测试id
     */
    @PostMapping("/delete/{tid}")
    public ResultVO<TestVO> teacherDeleteTest(@PathVariable Integer tid){ return testService.teacherDeleteTest(tid);}

    /**
     * 教师更新测试
     * @param  test 测试
     */
    @PostMapping("/update")
    public ResultVO<TestVO> teacherUpdateTest(@RequestBody TestVO test){
        return testService.teacherUpdateTest(test);
    }

    /**
     * 教师把题目插入到测试中
     * @param pid 题号 tid 测试号
     */
    @PostMapping("/insert")
    //public ResultVO<TestVO> InsertProblemIntoTest(@RequestParam(required = false, defaultValue ="" ) Integer pid, Integer tid){
    public ResultVO<TestVO> InsertProblemIntoTest(@RequestParam Integer pid,@RequestParam Integer tid){
        return testService.InsertProblemIntoTest(pid,tid);
    }

    /**
     * 教师把题目从测试中删除
     * @param pid 题号 tid 测试号
     */
    @PostMapping("/remove")
    public ResultVO<TestVO> RemoveProblemFromTest(@RequestParam Integer pid, @RequestParam  Integer tid){
        return testService.RemoveProblemFromTest(pid,tid);
    }
    /**
     * 学生提交测试
     * // @param tid 测试号 uid 用户编号 pidList 题号列表 studentAnsList 学生答案列表
     */
    @PostMapping("/submit")
    //public ResultVO<TestVO> studentSubmitTest(@RequestParam Integer tid, @RequestParam Integer uid, @RequestBody List<Integer> pidList, @RequestBody  List<String> studentAnsList){
    public ResultVO<TestVO> studentSubmitTest(@RequestBody List<StudentAnsVO> studentAnsVOList){
        //List<StudentAnsVO>studentAnsVOList = new ArrayList<>();
        //int i = 0;
        //while (i < pidList.size()){
        //StudentAnsVO stuVO = new StudentAnsVO();
        //stuVO.setTid(tid);
        //    stuVO.setUid(uid);
        //    stuVO.setPid(pidList.get(i));
        //    stuVO.setAnswer(studentAnsList.get(i));
        //    stuVO.setState(Ans_State.Accepted);//初值

        //    studentAnsVOList.add(stuVO);
        //    i++;
        //}
        return testService.studentSubmitTest(studentAnsVOList);
    }
    /**
     * 测试轮询触发器
     * @param tid 测试id
     */
    @GetMapping("/trigger")
    //TODO
    public ResultVO<TestVO> trigger(@RequestParam  Integer tid){
        return testService.trigger(tid);
    }
    /**
     * 学生查看
     * @param tid 测试id
     * @param uid 学生id
     */
    @GetMapping("/studentcheck")
    public ResultVO<StudentAttendTestVO> studentGetTest(@RequestParam  Integer tid,@RequestParam  Integer uid){
        return testService.studentGetTest(tid,uid);
    }
}
