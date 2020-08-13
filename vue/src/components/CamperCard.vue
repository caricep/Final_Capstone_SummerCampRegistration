<template>
  <div :id="id" class="card" :draggable="draggable" @dragstart="dragStart" @dragover.stop>
    <slot />
  </div>
</template>

<script>
export default {
  props: ["id", "draggable"],
  methods: {
    dragStart: event => {
      let target = event.target;
      // This has to match the getData method in our board
      event.dataTransfer.setData("card_id", target.id);
      setTimeout(() => {
        target.style.display = "none";
      }, 0);
    }
  }
};
</script>

<style>
</style>