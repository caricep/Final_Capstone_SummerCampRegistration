<template>
  <v-container>
    <v-img class="mt-n6" src="/Camping.jpg" contain="true" max-height="850"></v-img>
    <v-row no-gutters>
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
  </v-container>
  <!-- <div class="container mt-n6">

  </div>-->
</template>

<script>
export default {};
</script>

<style>
.container {
  height: 100%;
}
</style>