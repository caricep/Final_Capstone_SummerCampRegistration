<template v-slot:activator="{ on: editsCamper }">
  <v-dialog v-model="dialog" width="700" :camper="camper">
    <template v-slot:activator="{on: editsCamper}">
      <v-btn
        text
        color="red lighten-2"
        dark
        small
        v-on="editsCamper"
        @click="editCamper(camper)"
      >Edit Camper</v-btn>
    </template>
    <v-card>
      <v-card-title class="headline grey lighten-2">Edit Camper</v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-row>
            <v-text-field v-model="editedCamper.firstName" label="Camper First Name"></v-text-field>&nbsp;
            <v-text-field v-model="editedCamper.lastName" label="Camper Last Name"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.dateOfBirth" label="Date of Birth"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.streetAddress" label="Street"></v-text-field>&nbsp;
            <v-text-field v-model="editedCamper.streetAddressLineTwo" label="Street Line Two"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.city" label="City"></v-text-field>&nbsp;
            <v-text-field v-model="editedCamper.stateCode" label="State Code"></v-text-field>&nbsp;
            <v-text-field v-model="editedCamper.zipCode" label="Zip Code"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-if="editedCamper.paymentStatus" v-model="paid" label="Payment Status"></v-text-field>
            <v-text-field
              v-if="!editedCamper.paymentStatus"
              v-model="unpaid"
              label="Payment Status"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.familyContactName" label="Emergency Contact"></v-text-field>&nbsp;
            <v-text-field v-model="editedCamper.email" label="Contact Email"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.phoneNumber" label="Contact Number"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="editedCamper.additionalNotes" label="Camper Notes"></v-text-field>
          </v-row>
          <v-row justify="center">
            <v-btn class="success my-4" @click="submit()" :loading="isLoading">SUBMIT</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ["camper"],
  data() {
    return {
      paid: "Paid",
      unpaid: "Unpaid",
      editedCamper: "",
      isLoading: false,
      dialog: false
    };
  },
  methods: {
    paidOrUnpaid() {
      if (this.editedCamper.paymentStatus) {
        return "Paid";
      } else {
        return "Unpaid";
      }
    },
    editCamper(camper) {
      this.editedCamper = camper;
    },
    submit() {
      this.loading = true;
      // Submit this editedCamper to the API using a PUT
      console.log(this.editedCamper);
      this.loading = false;
      this.dialog = false;
      this.$emit("camperEdited");
      // Router push back to reload the campers page
    }
  }
};
</script>

<style>
</style>