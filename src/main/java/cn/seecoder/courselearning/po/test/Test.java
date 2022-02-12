package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.vo.test.TestVO;
import lombok.NonNull;

import java.util.Date;

public class Test {
    private Integer tid;
    private Integer cid;
    private Integer total_marks;
    private String testname;
    private Date starttime;
    private Date finishtime;
    private Test_State state;

    public Integer getTid() {
        return tid;
    }
    public void setTid(Integer id) {
        this.tid = id;
    }

    public Integer getCid() { return cid; }
    public void setCid(Integer id) {
        this.cid = id;
    }

    public String getTestname() { return testname; }
    public void setTestname(String testname) {
        this.testname= testname == null ? null : testname.trim();
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Test_State getState() {
        return state;
    }

    public void setState(Test_State state) {
        this.state = state;
    }

    public void setTotal_marks(int totalmarks){ this.total_marks = totalmarks;}

    public int getTotal_marks(){ return total_marks;}

    public Test(){

    }
    public Test(@NonNull TestVO testVO) {
        //TODO
        tid=testVO.getTid();
        cid=testVO.getCid();
        total_marks=testVO.getTotal_marks();
        testname=testVO.getTestname();
        starttime=testVO.getStarttime();
        finishtime=testVO.getFinishtime();
        state=testVO.getState();
    }

}
