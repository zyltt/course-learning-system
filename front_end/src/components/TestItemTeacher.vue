<template>
  <v-card :color="testColor" dark width="380" height="260" class="ma-4 pa-2">
    <v-card-title class="headline">
      {{ testName }}
    </v-card-title>
    <v-card-text class="text">
      开始时间：
      {{ startTime.format("yyyy-MM-dd hh:mm:ss") }}
    </v-card-text>
    <v-card-text class="text">
      结束时间：
      {{ endTime.format("yyyy-MM-dd hh:mm:ss") }}
    </v-card-text>
    <v-card-actions>
      <router-link
        :to="{
          path: `/teacher/testEdit/${this.$route.params.cid}/${tid}`,
          params: { cid: this.$route.params.cid, tid: tid }
        }"
      >
        <v-btn text>修改测试信息</v-btn>
      </router-link>
      <router-link
        :to="{
          path: `/teacher/testProblemManagement/${this.$route.params.cid}/${tid}`,
          params: { cid: this.$route.params.cid, tid: tid }
        }"
      >
        <v-btn text>管理测试题目</v-btn>
      </router-link>
      <v-btn text @click="deleteHandle">删除测试</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { teacherDeleteTest } from "@/api/test";
import Vue from "vue";
import "../api/format";
export default Vue.extend({
  name: "TestItemTeacher",
  props: {
    testName: {
      type: String,
      default: "testName"
    },
    tid: {
      type: Number,
      default: 0
    },
    startTime: {
      type: Date,
      default: Date.now()
    },
    endTime: {
      type: Date,
      default: Date.now()
    },
    testColor: {
      type: String,
      default: "#BCAAA4"
    }
  },
  methods: {
    deleteHandle() {
      const payload = {
        tid: this.tid,
        cid: this.$route.params.cid
      };
      console.log(payload);
      teacherDeleteTest(payload);
      this.$router.replace({
        path: `/teacher/TeacherTestList/${this.cid}`,
        params: { cid: this.cid }
      });
    }
  }
});
</script>

<style scoped>
.text {
  height: 40px;
  overflow: hidden;
}
</style>
