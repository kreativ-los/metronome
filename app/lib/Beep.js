const {AudioManager, AudioTrack, AudioFormat} = android.media;

const sampleRate = 44100;

export default class Beep {
  constructor(frequency, length) {
    this.frequency = frequency;
    this.length = length;

    this.tone = com.helpers.Beep.generateTone(frequency, length);
  }

  // generateSine() {
  //   const {length, frequency, volume} = this;
  //   const numSamples = length * sampleRate;
  //   const buffer = Array.create(java.lang.Short, numSamples);
  //
  //   for (let i = 0; i < numSamples; ++i) {
  //    const sample = new java.lang.Double(Math.sin(2 * Math.PI * i / (sampleRate / frequency)));
  //    buffer[i] = new java.lang.Short(sample * java.lang.Short.MAX_VALUE);
  //   }
  //
  //   this.numSamples = numSamples;
  //
  //   return buffer;
  // }
  //
  play() {
    const {tone} = this;

    tone.play();
  }
}
