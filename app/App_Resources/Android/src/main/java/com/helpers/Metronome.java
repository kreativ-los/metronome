package com.helpers;

import com.helpers.Beep;
import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;

public class Metronome {
  int speed;
  Beep mainTone;
  Beep secondaryTone;
  Timer timer;
  TimerTask tick;
  boolean isRunning = false;

  public Metronome(int bpm, Beep mainBeep, Beep secondaryBeep) {
    this.speed = bpm;
    this.mainTone = mainBeep;
    this.secondaryTone = secondaryBeep;
    this.timer = new Timer();
    this.tick = new TimerTask() {
      @Override
      public void run() {
        mainTone.play();
      }
    };
  }

  public void changeSpeed(int bpm) {
    this.speed = bpm;

    if (this.isRunning) {
      this.stop();
      this.timer.purge();
      this.start();
    }
  }

  public void start() {
    this.timer.scheduleAtFixedRate(this.tick, 0, 60000 / this.speed);
    this.isRunning = true;
  }

  public void stop() {
    this.timer.cancel();
    this.isRunning = false;
  }
}
