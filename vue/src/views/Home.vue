<template>
  <v-container>
    <div>
      <v-img class="mt-n6" src="/CampingCompressed.jpg" contain="true" max-height="850"></v-img>
      <v-row v-show="!loading" no-gutters>
        <v-col class="mt-4 text-right">
          <v-btn outlined rounded min-width="130px" color="white">Visitor</v-btn>
        </v-col>
        <v-col align="center" md="4">
          <v-img src="/logo_transparent.png" contain="true" max-height="300" class="mt-n16"></v-img>
        </v-col>
        <v-col class="mt-4">
          <v-btn outlined rounded color="white" router :to="{name: 'login'}">Otter Login</v-btn>
        </v-col>
      </v-row>
    </div>
  </v-container>
  <!-- <div class="container mt-n6">

  </div>-->
</template>

<script>
export default {
  data() {
    return {
      loading: false
    };
  },
  methods: {
    loadTime: function() {
      // Show the loader
      this.loading = true;

      setTimeout(() => {
        this.loading = false;
      }, 2000);
    }
  },
  created() {
    this.loadTime();
  }
};
</script>

<style>
.container {
  height: 100%;
}
</style>