<template>
  <v-container class="fill-height" fluid>
    <div
      class="alert alert-danger"
      role="alert"
      v-if="registrationErrors"
    >{{ registrationErrorMsg }}</div>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>Create Account</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-radio-group :mandatory="true" row hide-details>
            <v-spacer></v-spacer>
            <span class="grey--text">Select a user type:&nbsp;</span>
            <v-radio label="User" @change="registerFlipUser()"></v-radio>
            <v-radio label="Admin" @change="registerFlipAdmin()"></v-radio>
          </v-radio-group>
          <v-card-text>
            <v-form v-if="userRegister == true">
              <v-text-field
                label="Login"
                name="login"
                id="username"
                prepend-icon="mdi-account"
                type="text"
                v-model="user.username"
              ></v-text-field>
              <v-text-field
                id="confirmPassword"
                label="Password"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                required
                v-model="user.password"
              ></v-text-field>
              <v-text-field
                id="password"
                label="Confirm Password"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                required
                v-model="user.confirmPassword"
              ></v-text-field>
            </v-form>
            <v-form v-if="adminRegister == true">
              <v-text-field
                label="Login"
                name="login"
                id="username"
                prepend-icon="mdi-account"
                type="text"
                v-model="admin.username"
              ></v-text-field>
              <v-text-field
                id="confirmPassword"
                label="Password"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                required
                v-model="admin.password"
              ></v-text-field>
              <v-text-field
                id="password"
                label="Confirm Password"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                required
                v-model="admin.confirmPassword"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="registerUser()" color="primary">Submit</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      userRegister: true,
      adminRegister: false,
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user"
      },
      admin: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "admin"
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user."
    };
  },
  methods: {
    registerFlipUser() {
      this.userRegister = true;
      this.adminRegister = false;
    },
    registerFlipAdmin() {
      this.adminRegister = true;
      this.userRegister = false;
    },
    registerUser() {
      if (this.userRegister == true) {
        if (this.user.password != this.user.confirmPassword) {
          this.registrationErrors = true;
          this.registrationErrorMsg =
            "Password & Confirm Password do not match.";
        } else {
          authService
            .register(this.user)
            .then(response => {
              if (response.status == 201) {
                this.$router.push({
                  path: "/login",
                  query: { registration: "success" }
                });
              }
            })
            .catch(error => {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400) {
                this.registrationErrorMsg = "Bad Request: Validation Errors";
              }
            });
        }
      } else if (this.adminRegister == true) {
        if (this.admin.password != this.admin.confirmPassword) {
          this.registrationErrors = true;
          this.registrationErrorMsg =
            "Password & Confirm Password do not match.";
        } else {
          authService
            .register(this.admin)
            .then(response => {
              if (response.status == 201) {
                this.$router.push({
                  path: "/login",
                  query: { registration: "success" }
                });
              }
            })
            .catch(error => {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400) {
                this.registrationErrorMsg = "Bad Request: Validation Errors";
              }
            });
        }
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    }
  }
};
</script>

<style></style>
