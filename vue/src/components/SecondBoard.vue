<template>
  <div :id="id" class="board" @dragover.prevent @drop.prevent="drop">
    <slot />
  </div>
</template>

<script>
export default {
  props: ["id"],
  methods: {
    drop: function(event) {
      let card_id = event.dataTransfer.getData("card_id");
      let card = document.getElementById(card_id);
      card.style.display = "block";
      event.target.appendChild(card);
      this.$emit("camperDropped", card_id, event.target.id);
    }
  }
};
</script>

<style>
</style>