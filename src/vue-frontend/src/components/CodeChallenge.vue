<template>
  <div class="code-challenge">
    <h1>Food Fight</h1>
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
    fetch("/api/challenge/hello")
    .then((response) => response.text())
    .then((data) => {
      this.msg = data;
    });
  }
}
</script>

