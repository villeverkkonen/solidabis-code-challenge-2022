<template>
  <div class="code-challenge">
    <h1>The Masters of the Foodverse</h1>
    <div v-for="food in foods" v-bind:key="food.name">
      <p>Name: {{ food.name }}</p>
      <p>Health: {{ food.health }}</p>
      <p>Attack: {{ food.attack }}</p>
      <p>Defence: {{ food.defence }}</p>
      <p>Delay: {{ food.delay }}</p>
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
    };
  },
  mounted() {
    axios
      .get("/api/foods")
      .then((resp) => {
        this.foods = resp.data;
      })
      .catch((err) => console.log("Request Failed", err));
  },
});
</script>
