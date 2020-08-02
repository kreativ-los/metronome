<button
  class="icon"
  text="touch_app"
  on:tap={addTapTimestamp}
/>

<script>
  export let speed;

  let tapTimes = [];
  let timeout = null;
  let tapSpeed = 0;

  $: {
    if (tapTimes.length >= 2) {
      const differences = [];
      for (let i = 1; i < tapTimes.length; i++) {
        differences.push(tapTimes[i] - tapTimes[i - 1]);
      }
      const differenceSum = differences.reduce((a, b) => a + b, 0)
      const averageDifference = differenceSum / differences.length;
      tapSpeed = Math.round(60000 / averageDifference);
      speed = tapSpeed;
    }
  }

  function addTapTimestamp() {
    const date = new Date();
    tapTimes.push(date.getTime());
    tapTimes = tapTimes;

    if (timeout) clearTimeout(timeout);

    timeout = setTimeout(reset, 1000);
  }

  function reset() {
    tapTimes = [];
    tapSpeed = 0;
  }
</script>

<style>
  button {
    width: 300px;
    height: 300px;
    background-color: rgb(143, 216, 252);
    border-radius: 100%;
    font-size: 40px;
  }

  button:active {
    background-color: rgb(77, 148, 184);
  }
</style>
