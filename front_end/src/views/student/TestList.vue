<template>
  <div>
    <!--    <meta http-equiv="refresh" content="30;" />-->
    <v-container class="ma-8 pa-4">
      <v-row>
        <router-link :to="`/student`">
          <v-btn class="ma-4 mb-8" color="#3cd3f6">
            返回
          </v-btn>
        </router-link>
      </v-row>
      <v-row>
        <test-item
          v-for="test in testList"
          :key="test.tid"
          :testName="test.testName"
          :tid="test.tid"
          :startTime="test.startTime"
          :endTime="test.endTime"
          :state="test.state"
          :testColor="colorList[test.tid % colorList.length]"
        >
        </test-item>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import TestItem from "@/components/TestItem";
import { getCourseTests } from "@/api/test";
import { trigger } from "@/api/test";
export default {
  name: "TestList",
  components: {
    TestItem
  },
  data() {
    return {
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      testList: [],
      timer: null
    };
  },
  mounted() {
    console.log(this.$route.params.cid);
    console.log(this.testList);
    getCourseTests(this.$route.params.cid).then(res => {
      console.log(res);
      for (const re of res) {
        const start_temp_time = new Date(re.starttime);
        const finish_temp_time = new Date(re.finishtime);
        const now_temp_time = Date.now();
        const temp_state =
          now_temp_time < start_temp_time
            ? 0
            : now_temp_time < finish_temp_time
            ? 1
            : 2;
        this.testList.push({
          testName: re.testname,
          tid: re.tid,
          startTime: start_temp_time,
          endTime: finish_temp_time,
          state: temp_state
        });
        console.log(new Date(re.starttime));
        trigger(re.tid);
        console.log("state: " + temp_state);
      }
    });
  }
};
</script>
