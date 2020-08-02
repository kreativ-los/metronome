import 'globals';
import Metronome from './Metronome';

const metronome = new Metronome(120);

global.onmessage = (e) => {
  const {data} = e;

  metronome[data.cmd](data.val);
};

global.onerror = function(e) {
  console.error(e);
  return true;
}
