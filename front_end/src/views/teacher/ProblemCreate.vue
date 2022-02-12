<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="success"
      text
      v-show="showSuccessDialog"
      transition="scroll-y-transition"
    >
      题目创建成功！
    </v-alert>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="warning"
      text
      v-show="showFailDialog"
      transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="describe" label="题目内容"></v-text-field>
        <v-text-field v-model="answer" label="答案"></v-text-field>
        <v-text-field v-model="analysis" label="解析"></v-text-field>
        <v-text-field v-model="score" label="分值"></v-text-field>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { teacherCreateProblem } from "@/api/problem";

export default {
  name: "ProblemCreate",

  data() {
    return {
      value: 0,
      cid: this.$route.params.cid,
      describe: "",
      analysis: "",
      answer: "",
      msg: "",
      showSuccessDialog: false,
      showFailDialog: false,
      score: 0
    };
  },

  methods: {
    submit() {
      const payload = {
        describe: this.describe,
        answer: this.answer,
        analysis: this.analysis,
        value: this.score,
        cid: this.cid
      };
      console.log(payload);
      teacherCreateProblem(payload).then(res => {
        console.log(res.msg);
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    }
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
