import {setInterval, clearInterval} from '@nativescript/core/timer';

export default class Metronome {
  constructor(bpm, timeSignature = 4) {
    this.speed = bpm;
    this.timeSignature = timeSignature;

    this.mainTone = new com.helpers.Beep(1000, 60);
    this.secondaryTone = new com.helpers.Beep(800, 60);
    this.isRunning = false;
    this.currentTick = 0;
  }

  changeSpeed(bpm) {
    this.speed = bpm;

    if (this.isRunning) {
      this.stop();
      this.start();
    }
  }

  start() {
    this.timer = setInterval(() => {
      this.nextTone();
    }, 60000 / this.speed);

    this.isRunning = true;
  }

  stop() {
    clearInterval(this.timer);

    this.isRunning = false;
    this.currentTick = 0;
  }

  nextTone() {
    const beat = this.currentTick % this.timeSignature;

    if (beat === 0) {
      this.mainTone.play();
    } else {
      this.secondaryTone.play();
    }

    this.currentTick++;
  }
}
