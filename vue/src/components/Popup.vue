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
            <v-text-field
              v-model="camper.firstName"
              v-on:change="event => changeHandler(0, 'firstName', event)"
              label="Camper First Name"
            ></v-text-field>&nbsp;
            <v-text-field
              v-model="camper.lastName"
              v-on:change="event => changeHandler(1, 'lastName', event)"
              label="Camper Last Name"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.dateOfBirth"
              v-on:change="event => changeHandler(2, 'dateOfBirth', event)"
              label="Date of Birth"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.streetAddress"
              v-on:change="event => changeHandler(3, 'streetAddress', event)"
              label="Street"
            ></v-text-field>&nbsp;
            <v-text-field
              v-model="camper.streetAddressLineTwo"
              v-on:change="event => changeHandler(4, 'streetAddressLineTwo', event)"
              label="Street Line Two"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.city"
              v-on:change="event => changeHandler(5, 'city', event)"
              label="City"
            ></v-text-field>&nbsp;
            <v-text-field
              v-model="camper.stateCode"
              v-on:change="event => changeHandler(6, 'stateCode', event)"
              label="State Code"
            ></v-text-field>&nbsp;
            <v-text-field
              v-model="camper.zipCode"
              v-on:change="event => changeHandler(7, 'zipCode', event)"
              label="Zip Code"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-if="camper.paymentStatus"
              v-model="paid"
              v-on:change="event => changeHandler(8, 'paymentStatus', event)"
              label="Payment Status"
            ></v-text-field>
            <v-text-field
              v-if="!camper.paymentStatus"
              v-model="unpaid"
              v-on:change="event => changeHandler(8, 'paymentStatus', event)"
              label="Payment Status"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.familyContactName"
              v-on:change="event => changeHandler(9, 'familyContactName', event)"
              label="Emergency Contact"
            ></v-text-field>&nbsp;
            <v-text-field
              v-model="camper.email"
              v-on:change="event => changeHandler(10, 'email', event)"
              label="Contact Email"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.phoneNumber"
              v-on:change="event => changeHandler(11, 'phoneNumber', event)"
              label="Contact Number"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="camper.additionalNotes"
              v-on:change="event => changeHandler(12, 'additionalNotes', event)"
              label="Camper Notes"
            ></v-text-field>
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
import CamperService from "@/services/CamperService.js";
export default {
  props: ["camper"],
  data() {
    return {
      paid: "Paid",
      unpaid: "Unpaid",
      originalCamper: "",
      editedCamper: "",
      changesMade: [
        { firstName: "" },
        { lastName: "" },
        { dateOfBirth: "" },
        { streetAddress: "" },
        { streetAddressLineTwo: "" },
        { city: "" },
        { stateCode: "" },
        { zipCode: "" },
        { paymentStatus: "" },
        { familyContactName: "" },
        { email: "" },
        { phoneNumber: "" },
        { additionalNotes: "" }
      ],
      isLoading: false,
      dialog: false,
      firstName: ""
    };
  },
  methods: {
    changeHandler(index, propertyName, event) {
      let propertyToBeChanged = this.changesMade[index];
      propertyToBeChanged[propertyName] = event;
    },
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
      CamperService.updateCampers(this.editedCamper);
      this.$store.commit("SET_CHANGES_MADE", this.changesMade);

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