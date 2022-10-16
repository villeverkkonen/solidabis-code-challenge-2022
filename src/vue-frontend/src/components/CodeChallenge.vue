<template>
  <div class="codeChallenge">

    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>

    <div v-else>

      <div class="fighterSelectParentDiv">
        <div class="fighterSelect">
          <div class="fighterSelectOneStats">
            <span>Name: {{ fighterSelectOne.name }}</span>
            <span>Health: {{ fighterSelectOne.health }}</span>
            <span>Attack: {{ fighterSelectOne.attack }}</span>
            <span>Defence: {{ fighterSelectOne.defence }}</span>
            <span>Delay: {{ fighterSelectOne.delay }}</span>
          </div>

          <div class="fighterChangeButtons fighterChangeButtonsOne">
            <button class="fighterChangeButton fighterChangeButtonLeft"
              v-if="fighters[0].name !== fighterSelectOne.name"
              @click="changeFighter(`left`, fighterSelectOne, `fighterOne`)" data-direction="left"
              data-selectedFighter="this.fighterSelectOne">&lt;</button>
            <button class="fighterChangeButton fighterChangeButtonRight"
              v-if="fighters[fighters.length - 1].name !== fighterSelectOne.name"
              @click="changeFighter(`right`, fighterSelectOne, `fighterOne`)">></button>
          </div>
        </div>

        <div class="fighterSelect">
          <div class="fighterSelectTwoStats">
            <span>Name: {{ fighterSelectTwo.name }}</span>
            <span>Health: {{ fighterSelectTwo.health }}</span>
            <span>Attack: {{ fighterSelectTwo.attack }}</span>
            <span>Defence: {{ fighterSelectTwo.defence }}</span>
            <span>Delay: {{ fighterSelectTwo.delay }}</span>
          </div>

          <div class="fighterChangeButtons fighterChangeButtonsTwo">
            <button class="fighterChangeButton fighterChangeButtonLeft"
              v-if="fighters[0].name !== fighterSelectTwo.name"
              @click="changeFighter(`left`, fighterSelectTwo, `fighterTwo`)">&lt;</button>
            <button class="fighterChangeButton fighterChangeButtonRight"
              v-if="fighters[fighters.length - 1].name !== fighterSelectTwo.name"
              @click="changeFighter(`right`, fighterSelectTwo, `fighterTwo`)">></button>
          </div>
        </div>
      </div>



      <div class="fight">
        <button class="fightButton" @click="handleFight">Fight!</button>
      </div>

      <div class="fightLog" v-if="fightLog.length > 0">
        <div v-for="(log, index) in fightLog" v-bind:key="log">
          <div v-if="index === 0">
            <span class="playerOne">{{ log.startGame[0] }}</span>
            <span>{{ log.startGame[1] }}</span>
            <span class="playerTwo">{{ log.startGame[2] }}</span>
          </div>
          <div v-else-if="index === fightLog.length - 1" :class="[log.endGame[0]]">
            <span class="endGameText">{{ log.endGame[1] }}</span>
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
  margin: 5px 15px 5px 15px;
  font-size: 18px;
  width: 150px;
  height: 150px;
  color: #1D1C1A;
}

.fighterChangeButtons {
  margin-top: 15px;
}

.fighterChangeButton {
  background-color: #1D1C1A;
  border: none;
  color: #CEE5ED;
  border-radius: 5px;
  margin: 5px;
  font-size: 16px;
  font-weight: bold;
}

.fight {
  margin: 15px 0 15px 0;
}

.fighterSelectOneStats {
  background-color: #FAA0A0;
}

.fighterSelectTwoStats {
  background-color: #C1E1C1;
}

.fighterSelectOneStats,
.fighterSelectTwoStats {
  border-radius: 10px;
  padding: 2px 10px 2px 10px;
  text-align: left;
  border: solid #1D1C1A;
  box-shadow: 4px 8px #888888;
}

.fighterSelectOneStats span,
.fighterSelectTwoStats span {
  display: block;
  margin: 5px 0 5px 0;
}

.fightButton {
  background-color: #1D1C1A;
  border: none;
  color: #CEE5ED;
  border-radius: 5px;
  padding: 5px;
  font-weight: bold;
}

.fightLog {
  width: 70%;
  max-width: 600px;
  margin: auto;
  border-radius: 5px;
  border: solid #1D1C1A;
  box-shadow: 4px 8px #888888;
  color: #1D1C1A;
}

.playerOne {
  width: 100%;
  background-color: #FAA0A0;
}

.playerTwo {
  background-color: #C1E1C1;
}

.column {
  float: left;
  width: 32, 5%;
  text-align: left;
}

.columnOne {
  width: 15%;
}

.columnThree {
  float: right;
}

.row {
  border-bottom: 1px solid #1D1C1A;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.endGameText {
  font-weight: bold;
}

/* @media only screen and (min-width: 800px) {
  .fightLog {
    width: 50%;
  }
} */

@media only screen and (max-width: 600px) {

  .columnTwo,
  .columnThree {
    float: none;
    display: block;
    text-align: right;
  }
}

@media only screen and (max-width: 365px) {
  .fighterSelect {
    margin: 5px 10px 5px 10px;
    font-size: 16px;
    width: 140px;
    height: 140px;
  }
}
</style>