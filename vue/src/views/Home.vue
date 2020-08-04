<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <router-link :to="{ name: 'upload' }">Upload your campers</router-link>
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
