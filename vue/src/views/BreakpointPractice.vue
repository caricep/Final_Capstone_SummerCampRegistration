<template>
  <div class="home">
    <h1>Homepage</h1>
    <!-- This breakpoint class says 'hide this button on medium sized devices and down' -->
    <v-btn class="hidden-md-and-down">HIDDEN ON MEDIUM AND BELOW</v-btn>
    <!-- This breakpoint class says 'hide this button on medium sized devices and up -->
    <v-btn class="hidden-md-and-up">HIDDEN ON MEDIUM AND UP</v-btn>
    <!-- This breakpoint class says 'hide on small devices only' -->
    <!-- This one will appear on XS devices, because theyre not small, but xs -->
    <v-btn class="hidden-sm-only">HIDDEN ON SMALL ONLY</v-btn>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>