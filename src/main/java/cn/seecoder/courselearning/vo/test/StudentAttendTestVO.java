package cn.seecoder.courselearning.vo.test;

import java.util.List;

public class StudentAttendTestVO {
    private TestVO testVO;
    private List<ProblemVO> problemVOList;
    private List<String> studentAnswers;
    private List<Integer> studentmarks;
    private Integer get_marks;
    public TestVO getTestVO() {
        return testVO;
    }

    public void setTestVO(TestVO testVO) {
        this.testVO = testVO;
    }

    public List<Integer> getStudentmarks() {
        return studentmarks;
    }

    public void setStudentmarks(List<Integer> studentmarks) {
        this.studentmarks = studentmarks;
    }

    public List<ProblemVO> getProblemVOList() {
        return problemVOList;
    }

    public void setProblemVOList(List<ProblemVO> problemVOList) {
        this.problemVOList = problemVOList;
    }

    public List<String> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public Integer getGet_marks() {
        return get_marks;
    }

    public void setGet_marks(Integer get_marks) {
        this.get_marks = get_marks;
    }

    public StudentAttendTestVO(){

    }
    public StudentAttendTestVO(TestVO TestVO,List<ProblemVO> ProblemVOList,List<String> StudentAnswer,List<Integer> Studentmarks,Integer Get_marks){
        testVO=TestVO;
        problemVOList=ProblemVOList;
        studentAnswers=StudentAnswer;
        studentmarks=Studentmarks;
        get_marks=Get_marks;
    }

}
