<template>
  <div class="camperListItem">
    <h1>{{ camper.firstName }} {{ camper.lastName }}</h1>
    <ul class="camper-info">
      <!-- Turn this list into a table 
      Use a v-show to display table if camper is clicked-->
      <li>Birth Date: {{ camper.dateOfBirth }}</li>
      <li>Street Address: {{ camper.streetAddress }}</li>
      <li v-if="camper.streetAddressLineTwo">Address Line Two: {{ camper.streetAddressLineTwo }}</li>
      <li>City: {{ camper.city }}</li>
      <li>State: {{camper.stateCode }}</li>
      <li>Zip: {{camper.zipCode }}</li>
      <li>Camper ID: {{camper.camperID }}</li>
      <li>Payment Status: {{ camper.paymentStatus == true ? 'Paid' : 'Unpaid'}}</li>
      <li v-if="camper.additionalNotes">Additional Notes: {{ camper.additionalNotes }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "camper-list-item",
  props: ["camper"]
};
</script>

<style>
</style>