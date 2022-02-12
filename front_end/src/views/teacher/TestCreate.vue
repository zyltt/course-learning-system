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
      测试创建成功！
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
          label="开始时间(例：2022-01-02 12:12:12)"
        ></v-text-field>
        <v-text-field
          v-model="endTime"
          label="结束时间(例：2022-03-21 12:12:12)"
        ></v-text-field>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { teacherCreateTest } from "@/api/test";

export default {
  name: "TestCreate",

  data() {
    return {
      msg: "",
      testName: "",
      beginTime: "",
      endTime: "",
      cid: this.$route.params.cid,
      showSuccessDialog: false,
      showFailDialog: false
    };
  },
  methods: {
    submit() {
      const payload = {
        cid: this.$route.params.cid,
        testname: this.testName,
        starttime: this.beginTime,
        finishtime: this.endTime
      };
      console.log(payload);
      teacherCreateTest(payload).then(res => {
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
