package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.vo.test.StudentAttendTestVO;

public class TestAttend {
    private Integer tid;
    private Integer uid;
    private Integer get_score;

    public Integer getUid() {
        return uid;
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

    public Integer getGet_score() {
        return this.get_score;
    }

    public void setGet_score(Integer get_score) {
        this.get_score = get_score;
    }

    public TestAttend(){

    }

}
