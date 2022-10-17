<template>
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
</template>

<script>
export default {
  name: "FightLog",
  props: { fightLog: Object },
}
</script>

<style>
.fightLog {
  width: 70%;
  max-width: 600px;
  margin: auto;
  border-radius: 5px;
  border: solid #1D1C1A;
  box-shadow: 4px 8px #888888;
  color: #1D1C1A;
  margin-bottom: 20px;
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

@media only screen and (max-width: 600px) {

  .columnTwo,
  .columnThree {
    float: none;
    display: block;
    text-align: right;
  }
}
</style>