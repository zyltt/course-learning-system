<template>
  <v-card :color="problemColor" dark width="380" height="260" class="ma-4 pa-2">
    <v-card-text class="text">
      <v-row class="pa-2 pl-4"> 题目ID: {{ pid }} </v-row>
      <v-row class="pa-2 pl-4"> 分值: {{ score }} </v-row>
      <v-row class="pa-2 pl-4"> 题目描述: {{ describe }} </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn class="ml-0 mt-8 info" @click="addHandler">
        加入测试
      </v-btn>
      <v-btn class="ml-8 mt-8 info" @click="removeHandler">
        移出测试
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import Vue from "vue";
import { insertProblemIntoTest } from "@/api/test";
import { removeProblem } from "@/api/test";

export default Vue.extend({
  name: "ProblemItem",
  props: {
    cid: {
      type: Number,
      default: 0
    },
    score: {
      type: Number,
      default: 0
    },
    describe: {
      type: String,
      default: "空题目"
    },
    pid: {
      type: Number,
      default: 0
    },
    problemColor: {
      type: String,
      default: "#BCAAA4"
    }
  },
  methods: {
    addHandler() {
      const pid = this.pid;
      const tid = this.$route.params.tid;
      const payload = {
        pid,
        tid
      };
      insertProblemIntoTest(payload).then(res => {
        return res.msg();
      });
    },
    removeHandler() {
      const tid = this.$route.params.tid;
      const pid = this.pid;
      const payload = {
        pid,
        tid
      };
      removeProblem(payload).then(res => {
        return res.msg();
      });
    }
  }
});
</script>

<style scoped>
.text {
  min-height: 85px;
  overflow: hidden;
}
</style>
