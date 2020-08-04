<template>
  <div class="container">
    <!-- Bootstrap CSS to be changed out -->
    <div class="large-12 medium-12 small-12 cell">
      <label for="file">
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" />
      </label>
      <button v-on:click="submitFile()">Submit File</button>
    </div>
  </div>
</template>

<script>
import fileHandlerService from "@/services/FileHandlerService.js";

export default {
  name: "file-upload",
  data() {
    return {
      file: ""
    };
  },
  methods: {
    handleFileUpload() {
      // We utilize our ref=file above and grab the first item from the FileList browser object
      this.file = this.$refs.file.files[0];
    },
    submitFile() {
      // Create our form data object
      let formData = new FormData();
      // We then append the file to the formData using a key value pair
      formData.append("file", this.file);
      // Possibly add a form validator to make sure there is actually a file
      fileHandlerService
        .uploadFile(formData)
        .then(response => {
          if (response.status == "200") {
            console.log("File Upload Success");
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
</style>