<template>
  <div class="home">
    <h1>Homepage</h1>
    <!-- This is the vuetify button component -->
    <v-btn class="pink white--text">CLICK ME</v-btn>
    <!-- We can also use the attribute color to change the button color instead of the pink class -->
    <!-- The attribute depressed makes the button flush against the page and removes the background shadow -->
    <v-btn depressed color="pink">CLICK ME AGAIN</v-btn>
    <!-- flat attribute makes the button transparent and the text color pink.
    On hover, we'll get a light pink background appearing-->
    <v-btn flat color="pink">NO CLICK ME</v-btn>
    <v-btn depressed class="pink white--text">
      <!-- For placing a small icon in our button, we'll use v-icon -->
      <!-- We can find what icons are available from the material.io website -->
      <!-- We use the left prop to position the email icon further left of the text -->
      <v-icon left>email</v-icon>
      <span>Email me!</span>
    </v-btn>

    <!-- We can use the small prop to decrease the button size -->
    <v-btn depressed small class="pink white--text">
      <!-- We can also use small in the icon to make it smaller as well -->
      <v-icon left small>email</v-icon>
      <span>Email me!</span>
    </v-btn>

    <!-- and we can do the same with large -->
    <v-btn depressed large class="pink white--text">
      <span>Email me!</span>
      <v-icon right large>email</v-icon>
    </v-btn>

    <!-- A fab prop will make the button a circle around the icon -->
    <v-btn fab depressed small dark color="purple">
      <v-icon>favorite</v-icon>
    </v-btn>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>