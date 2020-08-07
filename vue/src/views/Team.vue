<template>
  <div class="team">
    <h1 class="subtitle-1 grey--text">Team</h1>

    <v-container class="my-10 mx-16">
      <v-row>
        <v-col
          cols="12"
          xs="12"
          sm="6"
          lg="3"
          text
          flat
          class="text-center ma-3"
          v-for="person in team"
          :key="person.name"
        >
          <v-card>
            <v-responsive class="pt-4">
              <v-avatar size="100">
                <img :src="person.avatar" alt="avatar" />
              </v-avatar>
            </v-responsive>
            <v-card-text>
              <div class="subheading">{{person.name}}</div>
              <div class="grey--text">{{person.role}}</div>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="grey">
                <v-icon small left>mdi-message</v-icon>
                <span>Message</span>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      team: [
        {
          name: "Sharron",
          role: "Camp Counselor",
          avatar: "/defaultavatar.png"
        },
        { name: "Joe", role: "Camp Advisor", avatar: "/defaultavatar.png" },
        { name: "Terry", role: "Camp Lead", avatar: "/defaultavatar.png" },
        {
          name: "Casey",
          role: "Junior Camp Counselor",
          avatar: "/defaultavatar.png"
        }
      ]
    };
  }
};
</script>

<style>
</style>