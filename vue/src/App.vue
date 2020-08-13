<template>
  <!-- v-app is our root Vuetify component. It MUST be our root component -->
  <v-app>
    <!-- v-app-bar is a nav-toolbar.  It is a vuetify component much like the components we create.
    This not only comes with pre-defined HTML but baked in functionality. We can see we pass the prop 'dark' here.
    If we were to change 'dark' to 'light' we would change the color scheme of the toolbar-->
    <!-- <v-app-bar app color="primary" dark> -->

    <!-- Within v-main is where we'll place all of our content for the page -->
    <!-- Using mx-4, were using margin on the x axis with a strength of 4 -->
    <v-main :class="this.$route.meta.homePage ? 'homepage' : 'grey lighten-4'">
      <Navbar v-if="!this.$route.meta.hideNavigation" class="mx-4" />
      <v-container :class="{'mx-5' : !this.$route.meta.homePage }">
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  name: "App",

  components: {
    Navbar
  },
  computed: {
    isAuthenticated() {
      return this.$store.state.user != "currentUser";
    }
  },

  data: () => ({
    //
  })
};
</script>

<style scoped>
html {
  background-color: #000;
}

.homepage {
  background-color: #297292;
}
</style>
