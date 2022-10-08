<template>
  <div class="code-challenge">
    <h1>The Masters of the Foodverse</h1>
    <p v-for="food in foods" v-bind:key="food.name">{{ food }}</p>
  </div>
</template>

<script>
export default {
  name: "CodeChallenge",
  data() {
    return {
      foods: [],
    };
  },
  mounted() {
    fetch("/api/foods")
      .then((resp) => resp.json())
      .then((data) => {
        this.foods = data;
      })
      .catch((err) => console.log("Request Failed", err));
  },
};
</script>
