package cn.seecoder.courselearning.po.test;

public class SelectProblem {
    private Integer tid;
    private Integer pid;
    public Integer getPid(){
        return this.pid;
    }
    public SelectProblem(Integer Tid, Integer Pid){
        this.tid = Tid;
        this.pid = Pid;
    }
}
