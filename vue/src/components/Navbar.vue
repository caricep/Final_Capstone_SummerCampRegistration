<template>
  <nav>
    <!-- We use flat to make the toolbar transparent.  We then use app
    to make the toolbar fixed-->
    <v-app-bar flat>
      <v-app-bar-nav-icon class="grey--text" @click="drawer = !drawer"></v-app-bar-nav-icon>
      <!-- Using toolbar title will position our spans in the top left of the toolbar -->
      <v-toolbar-title class="text-uppercase grey--text">
        <span>Camper</span>
        <span class="font-weight-light">Portal</span>
      </v-toolbar-title>
      <!-- We use a spacer to space our sign out button to the far right of the toolbar -->
      <v-spacer></v-spacer>
      <!-- text has replaced flat as the button property to clear the background color -->
      <v-btn text color="grey" router :to="{name: 'logout'}">
        <span>Sign Out</span>
        <!-- mdi and - are used now in Vuetify for material design icons -->
        <v-icon right>mdi-exit-to-app</v-icon>
      </v-btn>
    </v-app-bar>

    <!-- We use temporary here so that when we click away the nav drawer closes -->
    <v-navigation-drawer app v-model="drawer" temporary class="primary">
      <v-row justify="center">
        <v-col cols="6">
          <v-avatar size="100">
            <img src="/defaultavatar.png" />
          </v-avatar>
          <p class="white--text subheading mt-1">Administrator</p>
        </v-col>
      </v-row>
      <v-list>
        <v-list-item v-for="link in links" :key="link.text" router :to="link.route">
          <v-list-item-action>
            <v-icon class="white--text">{{link.icon}}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title class="white--text">{{link.text}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
      links: [
        { icon: "mdi-view-dashboard", text: "Dashboard", route: "/" },
        { icon: "mdi-folder", text: "My Campers", route: "/campers" },
        { icon: "mdi-account", text: "Team", route: "/team" },
        { icon: "mdi-upload", text: "Upload Campers", route: "/upload" },
        { icon: "mdi-history", text: "Change History", route: "/history" }
      ]
    };
  }
};
</script>

<style>
</style>