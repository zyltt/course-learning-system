package cn.seecoder.courselearning.vo.test;

import cn.seecoder.courselearning.enums.Test_State;
import cn.seecoder.courselearning.po.test.Test;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import lombok.Data;
import java.util.Date;
@Data
public class TestVO {
    private Integer tid;
    private Integer cid;
    private Integer total_marks;
    private String testname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishtime;
    private Test_State state;

    public TestVO(){
    }
    public TestVO(@NonNull Test test){
        tid=test.getTid();
        cid=test.getCid();
        total_marks = test.getTotal_marks();
        testname = test.getTestname();
        starttime = test.getStarttime();
        finishtime = test.getFinishtime();
        state = test.getState();
    }
}
