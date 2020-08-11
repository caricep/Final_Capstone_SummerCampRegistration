<template>
  <div class="campers">
    <h1 class="subtitle-1 grey--text">Campers</h1>
    <v-container class="my-10 mx-16">
      <v-snackbar v-model="snackbar" :timeout="4000" top color="success">
        <span class="font-weight-bold">Camper Successfully Edited!</span>
        <v-btn class="mx-4" text color="white" @click="snackbar=false">Close</v-btn>
      </v-snackbar>
      <!-- <div
        class="alert alert-success grey--text"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>-->
      <v-layout>
        <v-row align="end" class>
          <v-tooltip top>
            <template v-slot:activator="{ on: sortNames }">
              <v-btn
                small
                text
                color="grey"
                class="mb-1"
                @click="sortByLastName()"
                v-on="sortNames"
              >
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
            <template v-slot:activator="{ on: birthDate }">
              <v-btn
                small
                text
                color="grey"
                class="mb-1"
                @click="sortByDateOfBirth()"
                v-on="birthDate"
              >
                <v-icon left small>mdi-cake</v-icon>
                <span class="caption text-lowercase">By Date of Birth</span>
              </v-btn>
            </template>
            <span>Sort By Date of Birth</span>
          </v-tooltip>
          <v-tooltip top>
            <template v-slot:activator="{ on: sortPayments }">
              <v-btn
                small
                text
                color="grey"
                class="mb-1"
                @click="sortByPaymentStatus()"
                v-on="sortPayments"
              >
                <v-icon left small>mdi-checkbox-marked-circle</v-icon>
                <span class="caption text-lowercase">By Payment Status</span>
              </v-btn>
            </template>
            <span>Sort By Payment Status</span>
          </v-tooltip>

          <v-text-field
            label="Search camper name..."
            class="shrink mb-1"
            hide-details
            dense
            flat
            v-model="search"
            solo
            clearable
          ></v-text-field>
        </v-row>
      </v-layout>

      <v-card flat v-for="camper in filteredCampers" :key="camper.camperID">
        <v-row
          no-gutters
          :class="`pa-3 camper ${camper.paymentStatus == true ? 'Paid' : 'Unpaid'}`"
        >
          <v-col cols="12" sm="4" md="1">
            <div class="caption grey--text">Camper</div>
            <div>{{camper.firstName}} {{camper.lastName}}</div>
            <Popup
              :camper="camper"
              @camperEdited="snackbar=true"
              @camperUpdated="camperUpdated=true"
            />
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
                :color="`${camper.paymentStatus == true ? '#3B6B35' : '#ff4900'}`"
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
  computed: {
    filteredCampers: function() {
      return this.campers.filter(camper => {
        let fullName =
          camper.firstName.toLowerCase() + " " + camper.lastName.toLowerCase();
        return fullName.match(this.search.toLowerCase());
      });
    }
  },
  data() {
    return {
      campers: [],
      search: "",
      lastNameSortDirection: "ascending",
      paymentStatusSortDirection: "ascending",
      dateOfBirthSortDirection: "ascending",
      snackbar: false,
      camperUpdated: false
    };
  },
  methods: {
    saveCampers() {
      this.$store.commit("SET_CAMPERS", this.campers);
    },
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
    },
    sortByDateOfBirth() {
      if (this.dateOfBirthSortDirection == "ascending") {
        this.campers.sort((a, b) =>
          // Sort compares items next to eachother in an array
          // We'll get a 1 if we need to change the order, -1 if we don't need to change the order
          a.dateOfBirth < b.dateOfBirth ? 1 : -1
        );
        this.dateOfBirthSortDirection = "descending";
      } else {
        this.campers.sort((a, b) => (a.dateOfBirth < b.dateOfBirth ? -1 : 1));
        this.dateOfBirthSortDirection = "ascending";
      }
    }
  },
  created() {
    this.getCampers();
  },
  watch: {
    campers: function() {
      this.saveCampers();
    }
  }
};
</script>

<style>
.camper.Paid {
  border-left: 4px solid #3b6b35;
}

.camper.Unpaid {
  border-left: 4px solid #ff4900;
}

.searchRow {
  /* height: 40px;
  font-size: 30px;
  transform: scale(0.5, 0.5) translateX(50%); */
}
</style>