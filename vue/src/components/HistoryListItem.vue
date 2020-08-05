<template>
  <div>
    <h2>Change Type: {{history.changeType}}</h2>
    <ul>
      <!-- Change data output to table style formatting -->
      <li>Time: {{ history.changeDateAndTime }}</li>
      <li>Specifics: {{ history.changeSpecifics }}</li>
      <li>User: {{history.userName}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "history-list-item",
  props: ["history"]
};
</script>

<style>
</style>