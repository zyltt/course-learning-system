<template>
  <div>
    <v-container class="ma-8 pa-4">
      <!-- 全部题目信息 -->
      <v-row class="mt-8 mb-2">
        <v-chip
          class="ma-2"
          color="deep-purple lighten-3"
          label
          text-color="white"
        >
          <v-icon left>
            mdi-label
          </v-icon>
          课程全部题目
        </v-chip>
      </v-row>
      <v-row>
        <problem-item
          v-for="problem in allProblems"
          :cid="problem.cid"
          :pid="problem.pid"
          :key="problem.describe"
          :describe="problem.describe"
          :score="problem.value"
          :problem-color="colorList[problem.tid % colorList.length]"
        >
        </problem-item>
      </v-row>
      <!-- 已加入测试的题目 -->
      <v-row class="mt-8 mb-2">
        <v-chip
          class="ma-2"
          color="deep-purple lighten-3"
          label
          text-color="white"
        >
          <v-icon left>
            mdi-label
          </v-icon>
          测试使用题目
        </v-chip>
      </v-row>
      <v-row>
        <problem-item
          :pid="problem.pid"
          :key="problem.pid"
          v-for="problem in testProblems"
          :cid="problem.cid"
          :describe="problem.describe"
          :score="problem.value"
          :problem-color="colorList[problem.tid % colorList.length]"
        ></problem-item> </v-row
    ></v-container></div
></template>
<script>
import ProblemItem from "@/components/ProblemItem";
import { teacherGetProblemsByTestId } from "@/api/problem";
import { teacherGetAllProblemsInCourse } from "@/api/problem";

export default {
  name: "TestProblemManagement",
  components: {
    ProblemItem
  },
  data() {
    return {
      tid: this.$route.params.tid,
      cid: this.$route.params.cid,
      allProblems: [],
      testProblems: [],
      allProblemNum: 0,
      testProblemNum: 0,
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"]
    };
  },
  methods: {
    getAllProblems() {
      const cid = this.cid;
      teacherGetAllProblemsInCourse(cid).then(res => {
        console.log(`teacherGetAllProblemsInCourse`);
        console.log(res);
        this.allProblems = res || [];
        this.allProblemNum = res.length;
      });
    },
    getTestProblems() {
      const cid = this.cid;
      const tid = this.tid;
      const payload = { cid, tid };
      teacherGetProblemsByTestId(payload).then(res => {
        console.log(res.length);
        this.testProblems = res || [];
        console.log(this.testProblems);
        this.testProblemNum = res.length;
      });
    }
  },
  mounted() {
    this.getTestProblems();
    this.getAllProblems();
  }
};
</script>
