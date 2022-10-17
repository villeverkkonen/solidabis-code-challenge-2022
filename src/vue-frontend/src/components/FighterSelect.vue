
<template>
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
        <button class="fighterChangeButton fighterChangeButtonLeft" v-if="fighters[0].name !== fighterSelectOne.name"
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
        <button class="fighterChangeButton fighterChangeButtonLeft" v-if="fighters[0].name !== fighterSelectTwo.name"
          @click="changeFighter(`left`, fighterSelectTwo, `fighterTwo`)">&lt;</button>
        <button class="fighterChangeButton fighterChangeButtonRight"
          v-if="fighters[fighters.length - 1].name !== fighterSelectTwo.name"
          @click="changeFighter(`right`, fighterSelectTwo, `fighterTwo`)">></button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FighterSelect",
  props: {
    fighters: Array,
    fighterSelectOne: Object,
    fighterSelectTwo: Object,
    callback: Function
  },
  methods: {
    changeFighter(direction, selectedFighter, player) {
      if (this.callback) {
        this.callback(direction, selectedFighter, player);
      }
    }
  }
}
</script>

<style>
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

.fighterChangeButton:hover {
  cursor: pointer;
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
  padding: 0 5px 0 5px;
  text-align: left;
  border: solid #1D1C1A;
  box-shadow: 4px 8px #888888;
}

.fighterSelectOneStats span,
.fighterSelectTwoStats span {
  display: block;
  margin: 5px 0 5px 0;
}
</style>