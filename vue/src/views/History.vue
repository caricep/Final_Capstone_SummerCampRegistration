<template>
  <div class="history">
    <v-container class="my-10 mx-16">
      <v-row class="mb-3">
        <v-tooltip top>
          <template v-slot:activator="{ on: sortHistory }">
            <v-btn small text color="grey" @click="sortByChangeType()" v-on="sortHistory">
              <v-icon left small>mdi-label</v-icon>
              <span class="caption text-lowercase">By Change Type</span>
            </v-btn>
          </template>
          <span>Sort By Change Type</span>
        </v-tooltip>
      </v-row>

      <v-card flat v-for="history in histories" :key="history.historyID">
        <v-row no-gutters :class="`pa-3 history ${history.changeType}`">
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Change Type</div>
            <v-chip
              small
              :color="`${history.changeType == 'UPLOAD' ? '#3c99dc' : '#ef8f00'}`"
              class="white--text caption my-2"
            >{{history.changeType}}</v-chip>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">User</div>
            <div>{{history.userName}}</div>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Timestamp</div>
            <div>{{history.changeDateAndTime}}</div>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Change Specifics</div>
            <div>{{history.changeSpecifics}}</div>
            <!-- <div v-if="history.changeType == 'UPLOAD'">{{history.changeSpecifics}}</div>
            <div
              v-else
              v-for="change in historyChangesMade"
              :key="change.Id"
            >{{change.length > 0 ? change[0] : ''}}</div>-->
          </v-col>
        </v-row>
        <v-divider></v-divider>
      </v-card>

      <!-- <v-card flat v-for="history in histories" :key="history.historyID">
        <v-row
          v-if="history.changeType = 'UPLOAD'"
          no-gutters
          :class="`pa-3 history ${history.changeType}`"
        >
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Change Type</div>
            <v-chip
              small
              :color="`${history.changeType == 'UPLOAD' ? '#3c99dc' : '#ff9800'}`"
              class="white--text caption my-2"
            >{{history.changeType}}</v-chip>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">User</div>
            <div>{{history.userName}}</div>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Timestamp</div>
            <div>{{history.changeDateAndTime}}</div>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <div class="caption grey--text">Change Specifics</div>
            <div>{{history.changeSpecifics}}</div>
          </v-col>
        </v-row>
        <v-divider></v-divider>
      </v-card>-->
    </v-container>
  </div>
</template>

<script>
import historyService from "@/services/HistoryService.js";
export default {
  name: "history-list",
  components: {},
  data() {
    return {
      histories: [],
      vuexChangesMade: [],
      historyChangesMade: [],
      changeTypeSortDirection: "ascending"
    };
  },
  methods: {
    sortByChangeType() {
      if (this.changeTypeSortDirection == "ascending") {
        this.histories.sort((a, b) =>
          // Sort compares items next to eachother in an array
          // We'll get a 1 if we need to change the order, -1 if we don't need to change the order
          a.changeType < b.changeType ? -1 : 1
        );
        this.changeTypeSortDirection = "descending";
      } else {
        this.histories.sort((a, b) => (a.changeType < b.changeType ? 1 : -1));
        this.changeTypeSortDirection = "ascending";
      }
    },
    getAllHistory() {
      historyService.getHistory().then(response => {
        console.log(response.data);
        this.histories = response.data;
      });
    },
    getHistoryType(history) {
      if (history.changeType == "UPLOAD") {
        return "#3C99DC";
      }
    },
    grabChanges() {
      this.vuexChangesMade = this.$store.state.changesMade;
    },
    distributeChanges() {
      let firstChanges = this.vuexChangesMade[0];
      for (let change in firstChanges) {
        this.historyChangesMade.push(change);
      }
    }
  },
  created() {
    this.getAllHistory();
    this.grabChanges();
    this.distributeChanges();
  }
};
</script>

<style>
.history.UPLOAD {
  border-left: 4px solid #3c99dc;
}
.history.UPDATE {
  border-left: 4px solid #ef8f00;
}
</style>