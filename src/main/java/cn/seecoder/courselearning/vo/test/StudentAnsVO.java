package cn.seecoder.courselearning.vo.test;

import cn.seecoder.courselearning.enums.Ans_State;
import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.po.test.StudentAns;

public class StudentAnsVO {
    private Integer pid;
    private Integer uid;
    private Integer tid;
    private boolean state;
    private String answer;

    public void setState(boolean state) {
        this.state = state;
    }

    public void setState(Ans_State state) {
        if (state.equals(Ans_State.Accepted))
            this.state = true;
        if (state.equals(Ans_State.Wrong_Answer))
            this.state =false;
    }

    public Ans_State getState() {
        if (state)
            return Ans_State.Accepted;
        else
            return Ans_State.Wrong_Answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setPid(Integer pid) { this.pid = pid; }

    public Integer getPid() {
        return pid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }
    public StudentAnsVO() {
    }
    public StudentAnsVO(StudentAns studentAns){
        pid=studentAns.getPid();
        uid=studentAns.getUid();
        tid=studentAns.getTid();
        answer=studentAns.getAnswer();
        Ans_State State = studentAns.getState();
        if (State.equals(Ans_State.Accepted))
            this.state = true;
        if (State.equals(Ans_State.Wrong_Answer))
            this.state =false;
    }
}
