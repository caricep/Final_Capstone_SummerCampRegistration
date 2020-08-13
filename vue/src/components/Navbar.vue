<template>
  <nav>
    <!-- We use flat to make the toolbar transparent.  We then use app
    to make the toolbar fixed-->
    <v-app-bar flat>
      <v-app-bar-nav-icon class="grey--text" @click="drawer = !drawer"></v-app-bar-nav-icon>
      <!-- Using toolbar title will position our spans in the top left of the toolbar -->
      <v-toolbar-title class="text-uppercase grey--text">
        <span>LittleOtter</span>
        <span class="font-weight-light">&nbsp;Portal</span>
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
        <v-col cols="5">
          <v-avatar size="100">
            <img src="/defaultavatar.png" />
          </v-avatar>
          <p class="white--text subheading mt-1 mx-1 text-center text-capitalize">{{userName}}</p>
        </v-col>
      </v-row>
      <v-list>
        <v-list-item
          v-show="((userRole == 'ROLE_USER') && (link.role == 'ROLE_USER')) || userRole == 'ROLE_ADMIN'"
          v-for="link in links"
          :key="link.text"
          router
          :to="link.route"
        >
          <v-list-item-action
            v-if="((userRole == 'ROLE_USER') && (link.role == 'ROLE_USER')) || userRole == 'ROLE_ADMIN'"
          >
            <v-icon class="white--text">{{link.icon}}</v-icon>
          </v-list-item-action>
          <v-list-item-content
            v-if="((userRole == 'ROLE_USER') && (link.role == 'ROLE_USER')) || userRole == 'ROLE_ADMIN'"
          >
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
      userRole: "",
      userName: "",
      drawer: false,
      links: [
        {
          icon: "mdi-folder",
          text: "My Campers",
          route: "/campers",
          role: "ROLE_USER"
        },
        {
          icon: "mdi-account",
          text: "Team",
          route: "/team",
          role: "ROLE_USER"
        },
        {
          icon: "mdi-upload",
          text: "Add Campers",
          route: "/upload",
          role: ""
        },
        {
          icon: "mdi-account-switch",
          text: "Assign Dorms",
          route: "/dormassignments",
          role: ""
        },
        {
          icon: "mdi-email",
          text: "Send Emails",
          route: "/emails",
          role: "ROLE_USER"
        },
        {
          icon: "mdi-history",
          text: "Change History",
          route: "/history",
          role: "ROLE_USER"
        },

        {
          icon: "mdi-account-plus",
          text: "User Registration",
          route: "/register",
          role: ""
        }
      ]
    };
  },
  methods: {
    getRole() {
      this.userRole = this.$store.state.user.authorities[0].name;
      this.userName = this.$store.state.user.username;
    }
  },
  created() {
    this.getRole();
  },
  computed: {
    activeLinks: function() {
      return this.links.filter(function(link) {
        return link.admin;
      });
    }
  }
};
</script>

<style>
</style>