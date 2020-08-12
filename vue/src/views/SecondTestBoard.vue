<template>
  <div class="boxContainer">
    <main class="flexbox">
      <SecondBoard id="board-0" @camperDropped="change">
        <SecondCard
          :id="`${camper.camperID}`"
          v-for="camper in campers"
          :key="camper.camperID"
          draggable="true"
        >
          <p>{{camper.firstName}} {{camper.lastName}}</p>
        </SecondCard>
      </SecondBoard>
      <SecondBoard id="board-1" @camperDropped="change"></SecondBoard>
      <SecondBoard id="board-2" @camperDropped="change"></SecondBoard>
      <SecondBoard id="board-3" @camperDropped="change"></SecondBoard>
    </main>
  </div>
</template>

<script>
import SecondBoard from "@/components/SecondBoard.vue";
import SecondCard from "@/components/SecondCard.vue";

export default {
  data() {
    return {
      campers: [],
      dorm1: [],
      dorm2: [],
      dorm3: []
    };
  },
  components: {
    SecondBoard,
    SecondCard
  },
  methods: {
    change(card_id, board_id) {
      console.log("change");
      console.log(card_id);
      console.log(board_id);
      this.filteredDorm(card_id, board_id);
      //   this.filteredCampers(card_id);
    },
    filteredDorm(card_id, board_id) {
      if (board_id == "board-0") {
        for (let i = 0; i < this.dorm1.length; i++) {
          if (this.dorm1[i].camperID == card_id) {
            this.dorm1.splice(i, 1);
          }
        }
        for (let i = 0; i < this.dorm2.length; i++) {
          if (this.dorm2[i].camperID == card_id) {
            this.dorm2.splice(i, 1);
          }
        }
        for (let i = 0; i < this.dorm3.length; i++) {
          if (this.dorm3[i].camperID == card_id) {
            this.dorm3.splice(i, 1);
          }
        }
      }
      if (board_id == "board-1") {
        for (let i = 0; i < this.campers.length; i++) {
          if (this.campers[i].camperID == card_id) {
            this.dorm1.push(this.campers[i]);
          }
        }
        for (let i = 0; i < this.dorm2.length; i++) {
          if (this.dorm2[i].camperID == card_id) {
            this.dorm2.splice(i, 1);
          }
        }
        for (let i = 0; i < this.dorm3.length; i++) {
          if (this.dorm3[i].camperID == card_id) {
            this.dorm3.splice(i, 1);
          }
        }
      }
      if (board_id == "board-2") {
        for (let i = 0; i < this.campers.length; i++) {
          if (this.campers[i].camperID == card_id) {
            this.dorm2.push(this.campers[i]);
          }
        }
        for (let i = 0; i < this.dorm1.length; i++) {
          if (this.dorm1[i].camperID == card_id) {
            this.dorm1.splice(i, 1);
          }
        }
        for (let i = 0; i < this.dorm3.length; i++) {
          if (this.dorm3[i].camperID == card_id) {
            this.dorm3.splice(i, 1);
          }
        }
      }
      if (board_id == "board-3") {
        for (let i = 0; i < this.campers.length; i++) {
          if (this.campers[i].camperID == card_id) {
            this.dorm3.push(this.campers[i]);
          }
        }
        for (let i = 0; i < this.dorm1.length; i++) {
          if (this.dorm1[i].camperID == card_id) {
            this.dorm1.splice(i, 1);
          }
        }
        for (let i = 0; i < this.dorm3.length; i++) {
          if (this.dorm2[i].camperID == card_id) {
            this.dorm2.splice(i, 1);
          }
        }
      }
    }
    // filteredCampers(card_id) {
    //   let newCampers = this.campers.filter(obj => {
    //     return obj.camperID != card_id;
    //   });
    //   this.campers = newCampers;
    // }
  },
  created() {
    this.campers = this.$store.state.campers;
  }
};
</script>

<style>
.boardContainer {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.flexbox {
  display: flex;
  justify-content: space-between;

  width: 100%;
  max-width: 100vw;
  height: 60vh;

  overflow: hidden;

  margin: 0 auto;
  padding: 15px;
}

.flexbox .board {
  display: flex;
  flex-direction: column;

  width: 100%;
  max-width: 300px;

  background-color: #313131;

  padding: 15px;
}

.flexbox .board .card {
  padding: 15px 25px;
  background-color: #f3f3f3;

  cursor: pointer;
  margin-bottom: 15px;
}

.drop-zone {
  background-color: #eee;
  margin-bottom: 10px;
  padding: 10px;
}

.drag-el {
  background-color: #fff;
  margin-bottom: 10px;
  padding: 5px;
}
</style>