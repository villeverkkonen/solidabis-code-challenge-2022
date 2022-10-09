<template>
  <div class="code-challenge">
    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>
    <div v-else class="fighterSelectParentDiv">
      <div class="fighterSelect">
        <h3>Fighter 1</h3>
        <div class="fighterSelectOneStats">
          <p>Name: {{ fighterSelectOne.name }}</p>
          <p>Health: {{ fighterSelectOne.health }}</p>
          <p>Attack: {{ fighterSelectOne.attack }}</p>
          <p>Defence: {{ fighterSelectOne.defence }}</p>
          <p>Delay: {{ fighterSelectOne.delay }}</p>
        </div>
      </div>
      <div class="fighterSelect">
        <h3>Fighter 2</h3>
        <div class="fighterSelectTwoStats">
          <p>Name: {{ fighterSelectTwo.name }}</p>
          <p>Health: {{ fighterSelectTwo.health }}</p>
          <p>Attack: {{ fighterSelectTwo.attack }}</p>
          <p>Defence: {{ fighterSelectTwo.defence }}</p>
          <p>Delay: {{ fighterSelectTwo.delay }}</p>
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
      foods: [],
      loadingFighters: true,
      fighterSelectOne: null,
      fighterSelectTwo: null
    };
  },
  mounted() {
    axios
      .get("/api/foods")
      .then((resp) => {
        this.foods = resp.data;
        this.fighterSelectOne = resp.data[0];
        this.fighterSelectTwo = resp.data[2];
        this.loadingFighters = false;
      })
      .catch((err) => console.log("Request Failed", err));
  },
};
</script>

<style>
.fighterSelectParentDiv {
  text-align: center;
}

.fighterSelect {
  display: inline-block;
  margin: 0 20px 0 20px;
}
</style>