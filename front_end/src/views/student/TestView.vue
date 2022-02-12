<template>
  <div>
    <v-container class="pl-16 pr-16">
      <!-- alert -->
      <v-alert
        class="alert"
        outlined
        type="warning"
        text
        v-show="absent_flag"
        transition="scroll-y-transition"
      >
        {{ message }}
      </v-alert>

      <!--      //主体部分-->
      <v-card class="mx-auto grey lighten-5 mt-8" max-width="auto" outlined>
        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title
              v-show="!absent_flag"
              class="text-h5 mb-1"
              align="center"
            >
              您本次测试的总成绩为: {{ this.total_marks }}
            </v-list-item-title>
            <v-list-item-title
              v-show="absent_flag"
              class="text-h5 mb-1"
              align="center"
            >
              很遗憾，您未参加本次测试
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-card>

      <v-container class="pl-16 pr-16">
        <form
          class="pa-12 grey lighten-5 mt-8"
          v-for="problem in show_list"
          :key="problem.pid"
        >
          <v-text-field
            v-model="problem.des"
            label="题目内容"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="problem.target"
            label="满分"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="problem.stuGrade"
            label="学生得分"
            readonly
          ></v-text-field>
          <v-textarea
            v-model="problem.stuAnswer"
            label="学生答案"
            readonly
          ></v-textarea>
          <v-textarea
            v-model="problem.standardAnswer"
            label="标准答案"
            readonly
          ></v-textarea>
          <v-textarea
            v-model="problem.analysis"
            label="解析"
            readonly
          ></v-textarea>
        </form>
      </v-container>
      <div class="text-center mt-8">
        <v-btn rounded color="primary" dark @click="goback">
          &nbsp;返回&nbsp;
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import { studentGetTest } from "@/api/test";

export default {
  name: "TestView",
  data() {
    return {
      show_list: [],
      message: "",
      total_marks: 0,
      code: 0,
      absent_flag: false
    };
  },
  methods: {
    goback() {
      this.$router.go(-1);
    }
  },

  mounted() {
    const payload = {
      tid: Number(this.$route.params.tid),
      uid: Number(window.localStorage.getItem("userId"))
    };
    studentGetTest(payload).then(res => {
      this.code = res.code;
      this.message = res.msg;
      this.total_marks = res.data.get_marks;
      const n = res.data.problemVOList.length;
      for (let j = 0; j < n; j++) {
        this.show_list.push({
          pid: res.data.problemVOList[j].pid,
          des: res.data.problemVOList[j].describe,
          target: res.data.problemVOList[j].value,
          stuGrade: res.data.studentmarks[j],
          stuAnswer: res.data.studentAnswers[j],
          standardAnswer: res.data.problemVOList[j].answer,
          analysis: res.data.problemVOList[j].analysis
        });
      }
      console.log(this.show_list);
      if (this.message == "您未参加本场考试") {
        this.absent_flag = true;
      }
    });
  }
};
</script>

<style scoped>
.form {
  position: relative;
  top: 50px;
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
