//###################################该文件为新加的
import axios from "axios";
import { PROBLEM_MODULE } from "./_prefix";

/**
 * 获取某个题目信息
 * @param {*} payload
 * @returns
 */
export const teacherGetProblem = payload => {
  const { cid, pid } = payload;
  return axios.get(`${PROBLEM_MODULE}/${pid}?cid=${cid}`).then(res => {
    return res.data;
  });
};

/**
 * 获取当前课程的所有题目信息
 * @param {*} cid
 * @returns
 */
export const teacherGetAllProblemsInCourse = cid => {
  return axios.get(`${PROBLEM_MODULE}/cid/${cid}`).then(res => {
    return res.data;
  });
};
/**
 * 查看当前测试所有已添加的题目。
 * 该方法学生端和教师端通用。
 * @param {*} payload
 * @returns
 */
export const teacherGetProblemsByTestId = payload => {
  const { cid, tid } = payload;
  console.log(`teacherGetProblemsByTestId`);
  console.log(payload);
  return axios
    .get(`/api/problem/getTestProblems?cid=${cid}&tid=${tid}`)
    .then(res => {
      console.log(res);
      return res.data;
    });
};
/**
 * 创建一道题目 problem/create
 * @param {*} payload
 * @returns
 */
export const teacherCreateProblem = payload => {
  const { value, cid, describe, analysis, answer } = payload;
  console.log("teacherCreateProblem");
  console.log(payload);
  return axios
    .post(`${PROBLEM_MODULE}/create`, {
      value,
      cid,
      describe,
      analysis,
      answer
    })
    .then(res => {
      return res.data;
    });
};
/**
 * 删除某个题目
 * @param {*} payload
 * @returns
 */
export const teacherDeleteProblem = payload => {
  const { cid, pid } = payload;
  console.log(payload);
  return axios
    .post(`${PROBLEM_MODULE}/delete/${pid}`, {
      cid,
      pid
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
};

/**
 * 修改某个题目
 * @param {*} payload
 * @returns
 */
export const teacherUpdateProblem = payload => {
  const { describe, answer, analysis, cid, score, pid } = payload;
  console.log(payload);
  return axios
    .post(`${PROBLEM_MODULE}/set`, {
      describe,
      answer,
      analysis,
      cid,
      score,
      pid
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
};
