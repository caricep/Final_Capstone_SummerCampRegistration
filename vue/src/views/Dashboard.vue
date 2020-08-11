<template>
  <div class="dashboard">
    <h1 class="subtitle-1 grey--text">Dashboard</h1>
    <v-container class="my-10 mx-16">
      <v-expansion-panels>
        <v-expansion-panel v-for="change in changes" :key="change.id">
          <v-expansion-panel-header>{{change.changeType}}</v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-card flat>
              <v-card-text class="px-4 py-0 grey--text">
                <div class="font-weight-bold">Change made: {{change.changeDateAndTime}}</div>
                <div>{{change.changeSpecifics}}</div>
              </v-card-text>
            </v-card>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      snackbar: false
    };
  }
};
</script>

<style>
</style>