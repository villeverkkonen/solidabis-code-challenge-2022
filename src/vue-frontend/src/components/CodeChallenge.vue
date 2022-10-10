<template>
  <div class="codeChallenge">

    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>

    <div v-else>

      <div class="fighterSelectParentDiv">
        <div class="fighterSelect">
          <div class="fighterSelectOneStats">
            <p>Name: {{ fighterSelectOne.name }}</p>
            <p>Health: {{ fighterSelectOne.health }}</p>
            <p>Attack: {{ fighterSelectOne.attack }}</p>
            <p>Defence: {{ fighterSelectOne.defence }}</p>
            <p>Delay: {{ fighterSelectOne.delay }}</p>
          </div>

          <div class="fighterChangeButtonsOne">
            <button class="fighterChangeButtonLeft" v-if="fighters[0].name !== fighterSelectOne.name"
              @click="changeFighter(`left`, fighterSelectOne, `fighterOne`)" data-direction="left"
              data-selectedFighter="this.fighterSelectOne">&lt;</button>
            <button class="fighterChangeButtonRight" v-if="fighters[fighters.length - 1].name !== fighterSelectOne.name"
              @click="changeFighter(`right`, fighterSelectOne, `fighterOne`)">></button>
          </div>
        </div>

        <div class="fighterSelect">
          <div class="fighterSelectTwoStats">
            <p>Name: {{ fighterSelectTwo.name }}</p>
            <p>Health: {{ fighterSelectTwo.health }}</p>
            <p>Attack: {{ fighterSelectTwo.attack }}</p>
            <p>Defence: {{ fighterSelectTwo.defence }}</p>
            <p>Delay: {{ fighterSelectTwo.delay }}</p>
          </div>

          <div class="fighterChangeButtonsTwo">
            <button class="fighterChangeButtonLeft" v-if="fighters[0].name !== fighterSelectTwo.name"
              @click="changeFighter(`left`, fighterSelectTwo, `fighterTwo`)">&lt;</button>
            <button class="fighterChangeButtonRight" v-if="fighters[fighters.length - 1].name !== fighterSelectTwo.name"
              @click="changeFighter(`right`, fighterSelectTwo, `fighterTwo`)">></button>
          </div>
        </div>
      </div>



      <div class="fight">
        <button id="fightButton" @click="handleFight">Fight!</button>
      </div>

      <div class="fightLog" v-if="fightLog.length > 0">
        <div v-for="(log, index) in fightLog" v-bind:key="log">
          <div v-if="index === 0">
            <span class="playerOne">{{ log.startGame[0] }}</span>
            <span>{{ log.startGame[1] }}</span>
            <span class="playerTwo">{{ log.startGame[2] }}</span>
          </div>
          <div v-else-if="index === fightLog.length - 1" :class="[log.endGame[0]]">
            <span>{{ log.endGame[1] }}</span>
          </div>
          <div v-else class="row" :class=[log.row[0]]>
            <span class="column columnOne">{{log.row[1]}}</span>
            <span class="column columnTwo">{{log.row[2]}}</span>
            <span class="column columnThree">{{log.row[3]}}</span>
          </div>
        </div>
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

.fighterSelectOneStats {
  background-color: #ff6961;
}

.fighterSelectTwoStats {
  background-color: #77dd77;
}

.fighterSelectOneStats,
.fighterSelectTwoStats {
  border-radius: 10px;
  padding: 2px 10px 2px 10px;
}

.playerOne {
  width: 100%;
  background-color: #ff6961;
}

.playerTwo {
  background-color: #77dd77;
}

.column {
  float: left;
  width: 33.33%;
  text-align: left;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>