<template>
  <div>
    <h1>Send an Email</h1>
    <v-btn @click="sendEmail()">SEND</v-btn>
  </div>
</template>

<script>
import EmailService from "@/services/EmailService.js";

export default {
  data() {
    return {
      campers: []
    };
  },
  methods: {
    sendEmail() {
      EmailService.sendEmail(
        this.campers,
        "d-63c038f49692471fa75ca3f746dd7104"
      );
    }
  },
  created() {
    this.campers = this.$store.state.campers;
  }
};
</script>

<style>
</style>