<template>
  <div class="foodFight">

    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>

    <div v-else>

      <FighterSelect :fighters="fighters" :fighterSelectOne="fighterSelectOne" :fighterSelectTwo="fighterSelectTwo"
        :callback="changeFighter" />

      <div class="fight">
        <button class="fightButton" @click="handleFight">Fight!</button>
      </div>

      <FightLog :fightLog="fightLog" />

    </div>
  </div>
</template>

<script>
import axios from 'axios';
import FightLog from './FightLog.vue';
import FighterSelect from './FighterSelect.vue';

export default {
  name: "FoodFight",
  components: { FightLog, FighterSelect },
  data() {
    return {
      fighters: [],
      loadingFighters: true,
      fighterSelectOne: null,
      fighterSelectTwo: null,
      fightLog: []
    };
  },
  mounted() {
    axios
      .get("/api/fighters")
      .then((resp) => {
        this.fighters = resp.data;
        this.fighterSelectOne = resp.data[0];
        this.fighterSelectTwo = resp.data[resp.data.length - 1];
        this.loadingFighters = false;
      })
      .catch((err) => console.log("Request Failed", err));
  },
  methods: {
    async handleFight() {
      const res = await axios
        .post("/api/fighters/fight", [this.fighterSelectOne, this.fighterSelectTwo]);
      this.fightLog = res.data;
    },
    changeFighter(direction, selectedFighter, player) {
      let nextFighter = [];
      if (direction === "right") {
        nextFighter = this.fighters.filter(fighter => fighter.id === selectedFighter.id + 1);
      } else {
        nextFighter = this.fighters.filter(fighter => fighter.id === selectedFighter.id - 1);
      }
      if (nextFighter.length === 1) {
        player === "fighterOne" ? this.fighterSelectOne = nextFighter[0] : this.fighterSelectTwo = nextFighter[0];
      }
    },
  }
};
</script>

<style>
html {
  background-color: #CEE5ED;
}

.foodFight {
  text-align: center;
}

.fight {
  margin: 15px 0 15px 0;
}

.fightButton {
  background-color: #1D1C1A;
  border: none;
  color: #CEE5ED;
  border-radius: 5px;
  padding: 5px;
  font-weight: bold;
}

.fightButton:hover {
  cursor: pointer;
}
</style>