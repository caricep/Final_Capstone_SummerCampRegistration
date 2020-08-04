<template>
  <div>
    <h1>Upload Form</h1>
    <file-upload />
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