package cn.seecoder.courselearning.po.test;

import cn.seecoder.courselearning.vo.test.ProblemVO;
import lombok.NonNull;

public class Problem {
    private Integer pid;
    private Integer value;//分值
    private Integer cid;//course
    private String describe;
    private String analysis;
    private String answer;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer id) {
        this.pid = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCid() {
        return cid;
    }
    public void setCid(Integer id) {
        this.cid = id;
    }

    public String getDescribe() { return describe; }
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getAnalysis() { return analysis; }
    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();;
    }
    public Problem() {
    }
    public Problem(@NonNull ProblemVO problemVO) {
        //TODO
        pid=problemVO.getPid();
        value=problemVO.getValue();
        cid=problemVO.getCid();
        describe=problemVO.getDescribe();
        analysis=problemVO.getAnalysis();
        answer=problemVO.getAnswer();
    }


}
