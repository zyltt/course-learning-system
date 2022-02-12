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
      测试修改成功！
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
        <v-text-field v-model="testName" label="测试名称"></v-text-field>
        <v-text-field
          v-model="beginTime"
          label="开始时间(例：2021-01-02 12:12:12)"
        ></v-text-field>
        <v-text-field
          v-model="endTime"
          label="结束时间(例：2021-01-02 12:12:12)"
        ></v-text-field>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
        <router-link :to="`/teacher/TeacherTestList/${this.$route.params.cid}`">
          <v-btn class="ml-8 mt-8 info">
            返回
          </v-btn>
        </router-link>
      </form>
    </v-container>
  </div>
</template>

<script>
import { teacherUpdateTest } from "@/api/test";

export default {
  name: "TestEdit",

  data() {
    return {
      testName: "",
      beginTime: "",
      endTime: "",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      cid: this.$route.params.cid,
      tid: this.$route.params.tid
    };
  },
  methods: {
    submit() {
      const payload = {
        tid: Number(this.$route.params.tid),
        cid: Number(this.cid),
        testname: this.testName,
        starttime: this.beginTime,
        finishtime: this.endTime
      };
      console.log(payload);
      teacherUpdateTest(payload).then(res => {
        console.log(res.code);
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
