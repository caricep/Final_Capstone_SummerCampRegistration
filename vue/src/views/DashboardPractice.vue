<template>
  <div class="dashboard">
    <h1 class="subtitle-1 grey--text">Dashboard</h1>
    <v-container class="my-10 mx-16">
      <v-row no-gutters>
        <v-col cols="12" md="6" sm="12">
          <v-btn outlined block color="primary">1</v-btn>
        </v-col>
        <v-col cols="12" md="2" sm="4">
          <v-btn outlined block color="primary">2</v-btn>
        </v-col>
        <v-col cols="12" md="2" sm="4">
          <v-btn outlined block color="primary">2</v-btn>
        </v-col>
        <v-col cols="12" md="2" sm="4">
          <v-btn outlined block color="primary">2</v-btn>
        </v-col>
      </v-row>
      <v-row justify="space-around">
        <v-col cols="12" md="3" sm="4">
          <v-btn outlined block class="success">1</v-btn>
        </v-col>
        <v-col cols="12" md="3" sm="4">
          <v-btn outlined block class="success">2</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>