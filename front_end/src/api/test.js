//##########################################该文件是新加的
import axios from "axios";
/**
 * 获取当前课程的所有测试信息getCourseTests
 * @param {*} cid
 * @returns
 */
export const getCourseTests = cid => {
  console.log(`getCourseTests`);
  console.log(cid);
  return axios.get(`/api/test/cid/${cid}`).then(res => {
    console.log(res);
    return res.data;
  });
};
/**
 * 创建一个测试 test/create
 * @param {*} payload
 * @returns
 */
export const teacherCreateTest = payload => {
  const { cid, testname, starttime, finishtime } = payload;
  console.log("teacherCreateTest");
  console.log(payload);
  return axios
    .post(`/api/test/create`, {
      cid,
      testname,
      starttime,
      finishtime
    })
    .then(res => {
      return res.data;
    });
};

/**
 * 删除一个测试
 * @param {*} payload
 * @returns
 */
export const teacherDeleteTest = payload => {
  const { cid, tid } = payload;
  console.log(payload);
  return axios
    .post(`/api/test/delete/${tid}`, {
      cid,
      tid
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
};

/**
 * 修改某个题目teacherSetTest
 * @param {*} payload
 * @returns
 */
export const teacherUpdateTest = payload => {
  console.log("payload:---------");
  console.log(payload);
  return axios.post(`/api/test/update`, payload).then(res => {
    console.log(res);
    return res.data;
  });
};

/**
 * 学生查询某一测试
 * @param {*} payload
 * @returns
 */
export const studentGetTest = payload => {
  const { tid, uid } = payload;
  console.log("HTTP student get test");
  console.log(payload);
  return axios.get(`/api/test/studentcheck?tid=${tid}&uid=${uid}`).then(res => {
    console.log(res);
    return res.data;
  });
};
/**
 * 将指定题目加入测试InsertProblemIntoTest
 * @param {*} payload
 * @returns
 */
export const insertProblemIntoTest = payload => {
  const { pid, tid } = payload;
  console.log(payload);
  return axios
    .post(`/api/test/insert?pid=${pid}&tid=${tid}`, {
      tid,
      pid
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
};
/**
 * 将指定题目移除测试removeProblem
 * @param {*} payload
 * @returns
 */
export const removeProblem = payload => {
  const { pid, tid } = payload;
  console.log(payload);
  return axios
    .post(`/api/test/remove?pid=${pid}&tid=${tid}`, {
      pid,
      tid
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
};
/**
 * 查看当前测试所有已添加的题目
 * @param {*} payload
 * @returns
 */
export const getProblemsByTestId = payload => {
  console.log(`payload = ${payload}`);
  var { tid, cid } = payload;
  tid = Number(tid);
  cid = Number(cid);
  console.log(`tid = ${tid}, cid = ${cid}`);
  return axios
    .get(`/api/problem/getTestProblems?tid=${tid}&cid=${cid}`)
    .then(res => {
      console.log(res);
      return res.data;
    });
};

/**
 * 学生提交测试studentUpdateTest
 * @param {*} payload
 * @returns
 */
export const studentUpdateTest = payload => {
  console.log("payload");
  console.log(payload);
  return axios.post(`/api/test/submit`, payload).then(res => {
    console.log("update result");
    console.log(res);
    return res.data;
  });
};

/**
 *
 * @param {*} payload
 */
export const trigger = payload => {
  console.log("success!!!!!");
  const tid = Number(payload);
  axios.get(`/api/test/trigger?tid=${tid}`).then(res => {
    console.log(res);
  });
  console.log("success!!!!!");
};
