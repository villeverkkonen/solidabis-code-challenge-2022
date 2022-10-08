<template>
  <div class="code-challenge">
    <h1>The Masters of the Foodverse</h1>
    <p>{{ msg }}</p>
  </div>
</template>

<script>
export default {
  name: 'CodeChallenge',
  data() {
    return {
      msg: ''
    }
  },
  mounted() {
    fetch("/api/foods")
    .then((response) => response.text())
    .then((data) => {
      this.msg = data;
    });
  }
}
</script>

