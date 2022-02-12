<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-row>
        <router-link :to="`/teacher/testCreate/${this.$route.params.cid}`">
          <v-btn class="ma-4 mb-8" color="#3cd3f6">
            创建测试
          </v-btn>
        </router-link>
        <router-link :to="`/teacher/`">
          <v-btn class="ma-4 mb-8" color="#3cd3f6">
            返回
          </v-btn>
        </router-link>
      </v-row>
      <v-row>
        <test-item-teacher
          v-for="test in testList"
          :key="test.tid"
          :testName="test.testname"
          :tid="test.tid"
          :startTime="new Date(test.starttime)"
          :endTime="new Date(test.finishtime)"
          :course-color="colorList[test.tid % colorList.length]"
        >
        </test-item-teacher>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import TestItemTeacher from "@/components/TestItemTeacher";
import { getCourseTests } from "@/api/test";
export default {
  name: "TestList",
  components: {
    TestItemTeacher
  },
  data() {
    return {
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      testList: []
    };
  },
  mounted() {
    console.log(this.$route.params.cid);
    console.log(this.testList);
    getCourseTests(this.$route.params.cid).then(res => {
      console.log("res ----------- TestList");
      console.log(res);

      this.testList = res || [];
    });
  }
};
</script>
