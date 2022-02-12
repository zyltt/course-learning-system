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
    <v-card-text class="text">
      测试状态:
      {{ stateToString() }}
    </v-card-text>
    <v-card-actions>
      <router-link
        :to="{
          path: `/student/studentEdit/${this.$route.params.cid}/${tid}`,
          params: { cid: this.$route.params.cid, tid: tid }
        }"
      >
        <v-btn text v-show="state === 1">参加测试</v-btn>
      </router-link>
      <router-link
        :to="{
          path: `/student/view/${this.$route.params.cid}/${tid}`,
          params: { tid: tid }
        }"
      >
        <v-btn text v-show="state === 2">查看测试详情</v-btn>
      </router-link>
    </v-card-actions>
  </v-card>
</template>

<script>
import { teacherCreateTest } from "@/api/test";
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
    state: {
      type: Number,
      default: 1
    },
    testColor: {
      type: String,
      default: "#BCAAA4"
    }
  },
  methods: {
    stateToString() {
      if (this.state === 0) return "尚未开始";
      if (this.state === 1) return "正在进行";
      if (this.state === 2) return "已经结束";
      else return "state error";
    },
    deleteHandle() {
      const payload = {
        tid: this.tid,
        cid: this.$route.params.cid
      };
      console.log(payload);
      teacherCreateTest(payload);
      this.$router.replace({
        path: `/teacher`,
        params: { tid: this.tid }
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
