<template v-slot:activator="{ on: editsCamper }">
  <v-dialog v-model="dialog" width="700" :email="email">
    <template v-slot:activator="{on: editsCamper}">
      <v-btn color="red lighten-2" dark small v-on="editsCamper">Send Email</v-btn>
    </template>
    <v-card>
      <v-card-title class="headline grey lighten-2 center">{{email.name}} Email</v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-row justify="center">
            <v-col cols="12" sm="6">
              <v-select
                v-model="campersToEmail"
                :items="campers"
                :item-text="item =>`${item.firstName} ${item.lastName}`"
                :menu-props="{ maxHeight: '400' }"
                label="Select"
                multiple
                return-object
                hint="Pick campers"
                persistent-hint
              ></v-select>
            </v-col>
            <v-btn
              class="success my-4"
              @click="sendEmail(email.template, email.name)"
              :loading="isLoading"
            >SUBMIT</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import EmailService from "@/services/EmailService.js";
export default {
  props: ["email"],
  data() {
    return {
      campers: [],
      campersToEmail: [],
      dialog: false
    };
  },
  methods: {
    sendEmail(templateId, emailName) {
      console.log(templateId);
      console.log(emailName);
      if (emailName == "Welcome") {
        if (this.campersToEmail.length > 0) {
          EmailService.sendWelcome(this.campersToEmail, templateId);
        }
      } else if (emailName == "Invoice") {
        console.log("We made it");
        if (this.campersToEmail.length > 0) {
          EmailService.sendInvoice(this.campersToEmail, templateId);
        }
      } else if (emailName == "News") {
        if (this.campersToEmail.length > 0) {
          EmailService.sendNews(this.campersToEmail, templateId);
        }
      }
      this.dialog = false;
      this.$emit("emailSent");
    }
  },
  created() {
    this.campers = this.$store.state.campers;
  }
};
</script>

<style>
</style>