<template>
  <v-content>
    <v-container>
      <v-row justify="center">
        <v-col cols="6">
          <v-file-input v-model="file" label="Upload your Campers CSV" accept=".csv"></v-file-input>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="6">
          <v-btn color="primary" @click="submitFile()">Upload</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
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
          if (response.status == "201") {
            console.log("File Upload Success");
            this.$router.push({ name: "campers" });
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
</style>