<template>
  <div class="code-challenge">
    <h1>The Masters of the Foodverse</h1>
    <div v-if="loadingFighters">
      <p>Loading...</p>
    </div>
    <div v-else>
      <div class="fighterSelect">
        <h3>Fighter 1</h3>
        <div class="fighterSelectOneStats">
          <p>Name: {{ foods[0].name }}</p>
          <p>Health: {{ foods[0].health }}</p>
          <p>Attack: {{ foods[0].attack }}</p>
          <p>Defence: {{ foods[0].defence }}</p>
          <p>Delay: {{ foods[0].delay }}</p>
        </div>
      </div>
      <div class="fighterSelect">
        <h3>Fighter 2</h3>
        <div class="fighterSelectTwoStats">
          <p>Name: {{ foods[2].name }}</p>
          <p>Health: {{ foods[2].health }}</p>
          <p>Attack: {{ foods[2].attack }}</p>
          <p>Defence: {{ foods[2].defence }}</p>
          <p>Delay: {{ foods[2].delay }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: "CodeChallenge",
  data() {
    return {
      foods: [],
      loadingFighters: true
    };
  },
  mounted() {
    axios
      .get("/api/foods")
      .then((resp) => {
        console.log(resp.data);
        this.foods = resp.data;
        this.fighterSelectOne = resp.data[0];
        this.fighterSelectTwo = resp.data[2];
        this.loadingFighters = false;
      })
      .catch((err) => console.log("Request Failed", err));
  },
});
</script>
