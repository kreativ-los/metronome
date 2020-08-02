<stackLayout>
  <flexboxLayout>
    <stackLayout>
      <button class="icon" text="add" on:tap={increase}/>
      <button class="icon" text="remove" on:tap={decrease}/>
    </stackLayout>

    <flexboxLayout
      flexGrow="1"
      alignItems="center"
      justifyContent="center"
    >
      <textField
        class="speed"
        text="{speed}"
        width="80"
        keyboardType="number"
        maxLength="3"
        on:blur={changeSpeed}
        on:returnPress={changeSpeed}
      />

      <label>bpm</label>
    </flexboxLayout>

    <TapSpeed bind:speed/>
  </flexboxLayout>

  <flexboxLayout>
    <slider
      bind:value="{speed}"
      minValue="40"
      maxValue="250"
      flexGrow="1"
    />
  </flexboxLayout>
</stackLayout>

<script>
  import TapSpeed from './Tap';

  export let speed = 120;

  function changeSpeed(e) {
     const {text} = e.object;
     speed = text;
  }

  function increase() {
    speed++;
  }

  function decrease() {
    speed--;
  }
</script>

<style>
  .speed {
    font-weight: bold;
    font-size: 40px;
    text-align: center;
  }
</style>
