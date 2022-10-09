<template>
  <div class="codeChallenge">

    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>

    <div v-else>

      <div class="fighterSelectParentDiv">
        <div class="fighterSelect">
          <h3>Fighter 1</h3>
          <div class="fighterSelectOneStats">
            <p>Id: {{ fighterSelectOne.id }}</p>
            <p>Name: {{ fighterSelectOne.name }}</p>
            <p>Health: {{ fighterSelectOne.health }}</p>
            <p>Attack: {{ fighterSelectOne.attack }}</p>
            <p>Defence: {{ fighterSelectOne.defence }}</p>
            <p>Delay: {{ fighterSelectOne.delay }}</p>
          </div>

          <div>
            <button v-if="fighters[0].name !== fighterSelectOne.name" @click="changeFighterOneToLeft">&lt;</button>
            <button v-if="fighters[fighters.length - 1].name !== fighterSelectOne.name"
              @click="changeFighterOneToRight">></button>
          </div>
        </div>

        <div class="fighterSelect">
          <h3>Fighter 2</h3>
          <div class="fighterSelectTwoStats">
            <p>Id: {{ fighterSelectTwo.id }}</p>
            <p>Name: {{ fighterSelectTwo.name }}</p>
            <p>Health: {{ fighterSelectTwo.health }}</p>
            <p>Attack: {{ fighterSelectTwo.attack }}</p>
            <p>Defence: {{ fighterSelectTwo.defence }}</p>
            <p>Delay: {{ fighterSelectTwo.delay }}</p>
          </div>

          <div>
            <button v-if="fighters[0].name !== fighterSelectTwo.name" @click="changeFighterTwoToLeft">&lt;</button>
            <button v-if="fighters[fighters.length - 1].name !== fighterSelectTwo.name"
              @click="changeFighterTwoToRight">></button>
          </div>
        </div>
      </div>



      <div class="fight">
        <button id="fightButton" @click="handleFight">Fight!</button>
      </div>

      <div class="fightLog" v-if="fightLog.length > 0">
        <p v-for="log in fightLog" v-bind:key="log">{{ log }}</p>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "CodeChallenge",
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
    changeFighterOneToRight() {
      const nextFighter = this.fighters.filter(fighter => fighter.id === this.fighterSelectOne.id + 1);
      if (nextFighter.length === 1) {
        this.fighterSelectOne = nextFighter[0];
      }
    },
    changeFighterOneToLeft() {
      const nextFighter = this.fighters.filter(fighter => fighter.id === this.fighterSelectOne.id - 1);
      if (nextFighter.length === 1) {
        this.fighterSelectOne = nextFighter[0];
      }
    },
    changeFighterTwoToRight() {
      const nextFighter = this.fighters.filter(fighter => fighter.id === this.fighterSelectTwo.id + 1);
      if (nextFighter.length === 1) {
        this.fighterSelectTwo = nextFighter[0];
      }
    },
    changeFighterTwoToLeft() {
      const nextFighter = this.fighters.filter(fighter => fighter.id === this.fighterSelectTwo.id - 1);
      if (nextFighter.length === 1) {
        this.fighterSelectTwo = nextFighter[0];
      }
    }
  }
};
</script>

<style>
.codeChallenge {
  text-align: center;
}

.fighterSelect {
  display: inline-block;
  margin: 0 20px 0 20px;
}

.fight {
  margin-top: 10px;
}
</style>