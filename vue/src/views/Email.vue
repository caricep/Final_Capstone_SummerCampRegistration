<template>
  <div class="team">
    <h1 class="subtitle-1 grey--text">Emails</h1>

    <v-container class="my-10 mx-16">
      <v-snackbar v-model="snackbar" :timeout="4000" top color="success">
        <span class="font-weight-bold">Email Successfully Sent!</span>
        <v-btn class="mx-4" text color="white" @click="snackbar=false">Close</v-btn>
      </v-snackbar>
      <v-row justify="center">
        <v-col
          cols="12"
          xs="12"
          sm="6"
          lg="3"
          text
          flat
          class="text-center ma-3"
          v-for="email in emails"
          :key="email.name"
        >
          <v-card>
            <v-responsive class="pt-4">
              <v-img contain max-height="500px" :src="email.avatar"></v-img>
            </v-responsive>
            <v-card-text>
              <div class="subheading">{{email.name}} Email</div>
              <div class="grey--text">{{email.role}}</div>
            </v-card-text>
            <v-card-actions>
              <v-row justify="center">
                <Email-Popup :email="email" @emailSent="snackbar=true" />
              </v-row>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import EmailService from "@/services/EmailService.js";
import EmailPopup from "@/components/EmailPopup.vue";

export default {
  components: {
    EmailPopup
  },
  data() {
    return {
      snackbar: false,
      campers: [],
      emails: [
        {
          name: "Welcome",
          role: "Initial Welcome Email",
          avatar: "/LittleOtterWelcomeEmail.png",
          template: "d-63c038f49692471fa75ca3f746dd7104"
        },
        {
          name: "Invoice",
          role: "Camp Invoice",
          avatar: "/LittleOtterInvoice.png",
          template: "d-47c5b37df1bd47c8bc6b77bbd4908e05"
        },
        {
          name: "News",
          role: "Our Weekly Little Otter News",
          avatar: "/Newsletter.png",
          template: "d-ebbdf134325e4a8182b03282ad577e22"
        }
      ]
    };
  },
  methods: {},
  created() {
    this.campers = this.$store.state.campers;
  }
};
</script>

<style>
</style>