<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="success"
      text
      v-show="showSuccessSubmit"
      transition="scroll-y-transition"
    >
      提交成功！
    </v-alert>

    <v-alert
      class="alert"
      outlined
      type="warning"
      text
      v-show="repeat_flag"
      transition="scroll-y-transition"
    >
      重复提交，提交失败！
    </v-alert>

    <template>
      <v-dialog v-model="dialog" max-width="400" v-show="dialog">
        <v-card>
          <v-card-title>
            请确保每题完成，确定交卷吗？
          </v-card-title>
          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click.stop="submit">
              确定
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>

    <v-card
      class="mx-auto grey lighten-5 mt-8"
      max-width="auto"
      v-show="repeat_flag"
      outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title class="text-h5 mb-1" align="center">
            {{ msg }}
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-card>

    <v-container class="pl-16 pr-16">
      <form
        class="pa-12 grey lighten-5 mt-8"
        v-for="problem in problems_list"
        :key="problem.pid"
      >
        <v-text-field
          v-model="problem.des"
          label="题目内容"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="problem.val"
          label="分值"
          readonly
        ></v-text-field>
        <v-textarea
          v-model="problem.ans"
          label="请写出您的答案"
          id="aaa"
        ></v-textarea>
      </form>
    </v-container>
    <v-container class="pl-16 pr-16">
      <v-btn class="ml-0 mt-8 info" @click="dialog = true" id="submit">
        确认交卷
      </v-btn>
    </v-container>
    <v-container class="pl-16 pr-16"> </v-container>
    <v-container class="pl-16 pr-16"> </v-container>
    <v-card
      class="mx-auto grey lighten-5 mt-8"
      max-width="auto"
      v-show="repeat_flag"
      outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title class="text-h5 mb-1" align="center">
            {{ msg }}
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-card>
  </div>
</template>

<script>
import { getProblemsByTestId } from "@/api/test";
import { studentUpdateTest } from "@/api/test";

export default {
  name: "TestEdit",

  data() {
    return {
      problems_list: [],
      score: 0,
      cid: this.$route.params.cid,
      tid: this.$route.params.tid,
      uid: window.localStorage.getItem("userId"),
      showSuccessSubmit: false,
      dialog: false,
      repeat_flag: false,
      msg: "提交成功"
    };
  },

  methods: {
    submit() {
      this.dialog = false;
      console.log(this.problems_list);
      console.log(this.uid);

      // 调用提交接口

      var studentAnsVOList = [];
      for (const pro of this.problems_list) {
        studentAnsVOList.push({
          pid: Number(pro.pid),
          uid: Number(this.uid),
          tid: Number(this.tid),
          state: false,
          answer: pro.ans
        });
      }
      console.log("studentAnswersList");
      console.log(studentAnsVOList);

      studentUpdateTest(studentAnsVOList).then(res => {
        if (res.code == 0) {
          this.msg = res.msg;
          this.repeat_flag = true;
          this.showSuccessSubmit = false;
        } else {
          this.showSuccessSubmit = true;
        }
      });

      setTimeout(() => {
        this.$router.go(-1);
      }, 1000);
    }
  },

  mounted() {
    const payload = {
      tid: this.$route.params.tid,
      cid: this.$route.params.cid
    };
    getProblemsByTestId(payload).then(res => {
      console.log(res);
      for (const re of res) {
        this.problems_list.push({
          pid: re.pid,
          des: re.describe,
          val: re.value,
          ans: ""
        });
      }
    });
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
