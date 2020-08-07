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
            <v-text-field v-model="camper.firstName" label="Camper First Name"></v-text-field>&nbsp;
            <v-text-field v-model="camper.lastName" label="Camper Last Name"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.dateOfBirth" label="Date of Birth"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.streetAddress" label="Street"></v-text-field>&nbsp;
            <v-text-field v-model="camper.streetAddressLineTwo" label="Street Line Two"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.city" label="City"></v-text-field>&nbsp;
            <v-text-field v-model="camper.stateCode" label="State Code"></v-text-field>&nbsp;
            <v-text-field v-model="camper.zipCode" label="Zip Code"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-if="camper.paymentStatus" v-model="paid" label="Payment Status"></v-text-field>
            <v-text-field v-if="!camper.paymentStatus" v-model="unpaid" label="Payment Status"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.familyContactName" label="Emergency Contact"></v-text-field>&nbsp;
            <v-text-field v-model="camper.email" label="Contact Email"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.phoneNumber" label="Contact Number"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="camper.additionalNotes" label="Camper Notes"></v-text-field>
          </v-row>
          <v-row justify="center">
            <v-btn
              class="success my-4"
              @click="submit(), changesToCamper()"
              :loading="isLoading"
            >SUBMIT</v-btn>
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
      originalCamper: "",
      editedCamper: "",
      changesMade: [],
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
      this.originalCamper = camper;
      this.editedCamper = camper;
    },
    changesToCamper() {
      // const oType = this.originalCamper.GetProperties();

      for (let oProperty in Object.getOwnPropertyNames(this.originalCamper)) {
        let oOldValue = this.originalCamper.oProperty;
        let oNewValue = this.editedCamper.oProperty;

        if (oOldValue != oNewValue) {
          let sOldValue = oOldValue == null ? "null" : oOldValue.ToString();
          let sNewValue = oNewValue == null ? "null" : oNewValue.ToString();

          this.changesMade.push(
            "Property" + oProperty + " was: " + sOldValue + "; is: " + sNewValue
          );
        }
      }
    },
    submit() {
      this.isLoading = true;
      // Submit this editedCamper to the API using a PUT
      console.log(this.editedCamper);
      setTimeout((this.isLoading = false), 3000);
      this.dialog = false;
      this.$emit("camperEdited");

      // Router push back to reload the campers page
    },
    created() {
      this.editCamper(this.editedCamper);
    }
  }
};
</script>

<style>
</style>