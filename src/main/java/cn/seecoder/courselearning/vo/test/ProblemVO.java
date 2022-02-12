package cn.seecoder.courselearning.vo.test;

import cn.seecoder.courselearning.po.test.Problem;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProblemVO {
    private Integer pid;
    private Integer value;//分值
    private Integer cid;//course
    private String describe;
    private String analysis;
    private String answer;
    public ProblemVO(){

    }
    public ProblemVO(@NonNull Problem problem){
        pid=problem.getPid();
        value=problem.getValue();
        cid=problem.getCid();
        describe=problem.getDescribe();
        analysis=problem.getAnalysis();
        answer=problem.getAnswer();
    }
}

