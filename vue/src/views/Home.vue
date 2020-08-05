<template>
  <div class="home">
    <h1>Home</h1>
    <router-link :to="{ name: 'upload' }">Upload Your Campers</router-link>
    <br />
    <router-link :to="{name: 'history' }">View Change History</router-link>
    <camper-list />
  </div>
</template>

<script>
import CamperList from "@/components/CamperList.vue";

export default {
  name: "home",
  components: {
    CamperList
  }
};
</script>
