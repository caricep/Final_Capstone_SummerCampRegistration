<template>
  <div>
    <h1>List Of Changes</h1>
    <history-list-item v-for="history in histories" :key="history.historyID" :history="history" />
  </div>
</template>

<script>
import historyService from "@/services/HistoryService.js";
import HistoryListItem from "@/components/HistoryListItem.vue";
export default {
  name: "history-list",
  components: {
    HistoryListItem
  },
  data() {
    return {
      histories: []
    };
  },
  methods: {
    getAllHistory() {
      historyService.getHistory().then(response => {
        console.log(response.data);
        this.histories = response.data;
      });
    }
  },
  created() {
    this.getAllHistory();
  }
};
</script>

<style>
</style>