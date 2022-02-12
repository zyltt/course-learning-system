package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.enums.Ans_State;

import cn.seecoder.courselearning.vo.test.StudentAnsVO;
import lombok.NonNull;

public class StudentAns {
    private Integer pid;
    private Integer uid;
    private Integer tid;
    private Ans_State state;
    private String answer;

    public void setState(Ans_State state) {
        this.state = state;
    }

    public Ans_State getState() {
        return state;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public StudentAns() {

    }
    public StudentAns(@NonNull StudentAnsVO studentAnsVO){
        pid=studentAnsVO.getPid();
        uid=studentAnsVO.getUid();
        tid=studentAnsVO.getTid();
        state=studentAnsVO.getState();
        answer = studentAnsVO.getAnswer();
        //TODO
    }
}
