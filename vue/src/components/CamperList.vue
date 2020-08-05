<template>
  <div>
    <h1 v-if="campers.length > 0">Camper List</h1>
    <camper-list-item v-for="camper in campers" :key="camper.camperID" :camper="camper" />
  </div>
</template>

<script>
import camperService from "@/services/CamperService.js";
import CamperListItem from "@/components/CamperListItem.vue";

export default {
  name: "camper-list",
  components: {
    CamperListItem
  },
  data() {
    return {
      campers: []
    };
  },
  methods: {
    getCampers() {
      camperService.getAllCampers().then(response => {
        console.log(response.data);
        this.campers = response.data;
      });
    }
  },
  created() {
    this.getCampers();
  }
};
</script>

<style>
</style>