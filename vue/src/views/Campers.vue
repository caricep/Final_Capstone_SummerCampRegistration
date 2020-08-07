<template>
  <div class="campers">
    <h1 class="subtitle-1 grey--text">Campers</h1>
    <v-container class="my-10 mx-16">
      <v-snackbar v-model="snackbar" :timeout="4000" top color="success">
        <span class="font-weight-bold">Camper Successfully Edited!</span>
        <v-btn class="mx-4" text color="white" @click="snackbar=false">Close</v-btn>
      </v-snackbar>
      <v-row class="mb-3">
        <v-tooltip top>
          <template v-slot:activator="{ on: sortNames }">
            <v-btn small text color="grey" @click="sortByLastName()" v-on="sortNames">
              <v-icon left small>mdi-account</v-icon>
              <span class="caption text-lowercase">By Camper Name</span>
            </v-btn>
          </template>
          <span>Sort By Camper</span>
        </v-tooltip>
        <!-- <v-btn small text color="grey" @click="sortBy('dateOfBirth')">
          <v-icon left small>mdi-calendar</v-icon>
          <span class="caption text-lowercase">By Date of Birth</span>
        </v-btn>-->
        <v-tooltip top>
          <template v-slot:activator="{ on: sortPayments }">
            <v-btn small text color="grey" @click="sortByPaymentStatus()" v-on="sortPayments">
              <v-icon left small>mdi-checkbox-marked-circle</v-icon>
              <span class="caption text-lowercase">By Payment Status</span>
            </v-btn>
          </template>
          <span>Sort By Payment Status</span>
        </v-tooltip>
      </v-row>

      <v-card flat v-for="camper in campers" :key="camper.camperID">
        <v-row
          no-gutters
          :class="`pa-3 camper ${camper.paymentStatus == true ? 'Paid' : 'Unpaid'}`"
        >
          <v-col cols="12" sm="4" md="1">
            <div class="caption grey--text">Camper</div>
            <div>{{camper.firstName}} {{camper.lastName}}</div>
            <Popup :camper="camper" @camperEdited="snackbar=true" />
          </v-col>
          <v-col cols="12" sm="4" md="1">
            <div class="caption grey--text">Date Of Birth</div>
            <div>{{camper.dateOfBirth}}</div>
          </v-col>
          <v-col cols="12" sm="4" md="2">
            <div class="caption grey--text">Address</div>
            <div>
              <span>{{camper.streetAddress}},</span>&nbsp;
              <span v-if="camper.streetAddressLineTwo">{{camper.streetAddressLineTwo}},</span>
            </div>
            <div>
              <span>{{camper.city}}, &nbsp;</span>
              <span>{{camper.stateCode}} &nbsp;</span>
              <span>{{camper.zipCode}}</span>
            </div>
          </v-col>
          <v-col cols="2" sm="4" md="1">
            <div class="caption grey--text">Payment Status</div>
            <div class="right">
              <v-chip
                small
                :color="`${camper.paymentStatus == true ? '#76ba1b' : '#ff9800'}`"
                class="white--text caption my-2"
              >{{camper.paymentStatus == true ? 'Paid' : 'Unpaid'}}</v-chip>
            </div>
          </v-col>
          <v-col cols="12" sm="4" md="1">
            <div class="caption grey--text">Emergency Contact</div>
            <div>{{camper.familyContactName}}</div>
          </v-col>
          <v-col cols="12" sm="4" md="2">
            <div class="caption grey--text">Email</div>
            <div>{{camper.email}}</div>
          </v-col>
          <v-col cols="12" sm="4" md="1">
            <div class="caption grey--text">Phone Number</div>
            <div>{{camper.phoneNumber}}</div>
          </v-col>
          <v-col cols="12" sm="4" md="3">
            <div class="caption grey--text">Camper Notes</div>
            <p>
              <span v-if="!camper.additionalNotes" class="body-2">No notes added</span>
              <span class="body-2">{{camper.additionalNotes}}</span>
            </p>
          </v-col>
        </v-row>
        <v-divider></v-divider>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import camperService from "@/services/CamperService.js";
import Popup from "@/components/Popup.vue";
// import CamperList from "@/components/CamperList.vue";

export default {
  name: "campers",
  components: {
    // CamperList
    Popup
  },
  computed: {},
  data() {
    return {
      campers: [],
      lastNameSortDirection: "ascending",
      paymentStatusSortDirection: "ascending",
      stateCodes: [
        "AL",
        "AK",
        "AS",
        "AZ",
        "AR",
        "CA",
        "CO",
        "CT",
        "DE",
        "DC",
        "FM",
        "FL",
        "GA",
        "GU",
        "HI",
        "ID",
        "IL",
        "IN",
        "IA",
        "KS",
        "KY",
        "LA",
        "ME",
        "MH",
        "MD",
        "MA",
        "MI",
        "MN",
        "MS",
        "MO",
        "MT",
        "NE",
        "NV",
        "NH",
        "NJ",
        "NM",
        "NY",
        "NC",
        "ND",
        "MP",
        "OH",
        "OK",
        "OR",
        "PW",
        "PA",
        "PR",
        "RI",
        "SC",
        "SD",
        "TN",
        "TX",
        "UT",
        "VT",
        "VI",
        "VA",
        "WA",
        "WV",
        "WI",
        "WY"
      ],
      snackbar: false
    };
  },
  methods: {
    getCampers() {
      camperService.getAllCampers().then(response => {
        console.log(response.data);
        this.campers = response.data;
      });
    },
    sortByLastName() {
      if (this.lastNameSortDirection == "ascending") {
        this.campers.sort((a, b) =>
          // Sort compares items next to eachother in an array
          // We'll get a 1 if we need to change the order, -1 if we don't need to change the order
          a.lastName < b.lastName ? -1 : 1
        );
        this.lastNameSortDirection = "descending";
      } else {
        this.campers.sort((a, b) => (a.lastName < b.lastName ? 1 : -1));
        this.lastNameSortDirection = "ascending";
      }
    },
    sortByPaymentStatus() {
      if (this.paymentStatusSortDirection == "ascending") {
        this.campers.sort((a, b) =>
          // Sort compares items next to eachother in an array
          // We'll get a 1 if we need to change the order, -1 if we don't need to change the order
          a.paymentStatus < b.paymentStatus ? 1 : -1
        );
        this.paymentStatusSortDirection = "descending";
      } else {
        this.campers.sort((a, b) =>
          a.paymentStatus < b.paymentStatus ? -1 : 1
        );
        this.paymentStatusSortDirection = "ascending";
      }
    }
  },
  created() {
    this.getCampers();
  }
};
</script>

<style>
.camper.Paid {
  border-left: 4px solid #76ba1b;
}

.camper.Unpaid {
  border-left: 4px solid #ff9800;
}
</style>