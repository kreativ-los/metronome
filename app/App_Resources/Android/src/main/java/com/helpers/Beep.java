package com.helpers;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.AudioFormat;

public class Beep {
  double frequency;
  int length;
  short[] samples;
  int sampleCount;
  AudioTrack track;

  public Beep(double freq, int leng) {
    this.frequency = freq;
    this.length = leng;
    this.samples = this.generateTone();

    this.track = new AudioTrack(
      AudioManager.STREAM_MUSIC,
      44100,
      AudioFormat.CHANNEL_OUT_STEREO,
      AudioFormat.ENCODING_PCM_16BIT,
      this.sampleCount * (Short.SIZE / 8),
      AudioTrack.MODE_STREAM
    );
  }

  public short[] generateTone() {
    int count = (int)(44100.0 * 2.0 * (length / 1000.0)) & ~1;
    short[] samples = new short[count];

    for(int i = 0; i < count; i += 2) {
      short sample = (short)(Math.sin(2 * Math.PI * i / (44100.0 / frequency)) * 0x7FFF);
      samples[i] = sample;
      samples[i + 1] = sample;
    }

    this.sampleCount = count;

    return samples;
  }

  public void play() {
    track.write(samples, 0, this.sampleCount);

    if (this.track.getPlayState() != AudioTrack.PLAYSTATE_PLAYING) {
      track.play();
    }
  }

  public void stop() {
    track.stop();
  }
}
