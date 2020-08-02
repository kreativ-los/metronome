<button
  class="icon"
  text={isPlaying ? 'pause_circle_filled' : 'play_circle_filled'}
  on:tap={toggleMetronome}
/>

<script>
  // import Metronome from '../../lib/Metronome';
  import MetronomeWorker from 'nativescript-worker-loader!../../lib/metronomeWorker.js';

  export let speed = 120;

  let isPlaying = false;
  const metronomeWorker = new MetronomeWorker();

  $: {
    if (speed) {
      metronomeWorker.postMessage({
        cmd: 'changeSpeed',
        val: speed
      });
    }
  }

  function toggleMetronome() {
    if (isPlaying) {
      metronomeWorker.postMessage({
        cmd: 'stop'
      });
    } else {
      metronomeWorker.postMessage({
        cmd: 'start'
      });
    }

    isPlaying = !isPlaying;
  }
</script>

<style>
  button {
    font-size: 80px;
    width: 300px;
    height: 300px;
  }
</style>
