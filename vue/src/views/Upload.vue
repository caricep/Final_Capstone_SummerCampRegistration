<template>
  <div>
    <h1 class="subtitle-1 grey--text">Upload Form</h1>

    <v-container class="my-10 mx-16">
      <file-upload />
    </v-container>
  </div>
</template>

<script>
import FileUpload from "@/components/FileUpload.vue";

export default {
  name: "upload",
  components: {
    FileUpload
  }
};
</script>

<style>
</style>