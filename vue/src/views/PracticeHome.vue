<template>
  <div class="home">
    <!-- <h1>Home</h1>
    <router-link :to="{ name: 'upload' }">Upload Your Campers</router-link>
    <br />
    <router-link :to="{name: 'history' }">View Change History</router-link>
    <camper-list />-->
    <h1>Homepage</h1>
    <p
      class="green white--text"
    >Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, nihil! Saepe, porro corrupti blanditiis hic placeat architecto alias necessitatibus quis ullam eum, aut a nostrum aspernatur consequuntur enim, quo tempora!</p>
    <!-- lighten-4 lightens the background color to the degree of the number we choose -->
    <!-- text--darken-4 darkens the text color by 4 steps -->
    <p
      class="pink lighten-4 red--text text--darken-4"
    >Lorem ipsum dolor sit amet consectetur, adipisicing elit. Vitae fugiat laborum ipsam sapiente, impedit facilis accusantium soluta quis numquam blanditiis in, distinctio quo fugit, saepe officiis quia voluptatem odio doloremque.</p>
    <!-- Using display-4 will give you the largest text size we can have -->
    <h1 class="display-4">Massive Display</h1>
    <!-- display-1 is our smallest font display we can use.  Its STILL bigger than a regular h1 though -->
    <h4 class="display-1">Small Display</h4>
    <!-- Below are other displays of text sizes we can use -->
    <p class="headline">This is a headline</p>
    <p class="subheading font-weight-bold">This is a sub-heading</p>
    <p class="caption">This is a caption</p>
  </div>
</template>

<script>
// import CamperList from "@/components/CamperList.vue";

export default {
  name: "home",
  components: {
    // CamperList
  }
};
</script>
