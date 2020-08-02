<stackLayout>
  {#if name}
    <label class="name">{name}</label>
  {/if}

  <label class="heading">Geschwindigkeit</label>

  <Speed bind:speed/>

  <button on:tap={getSongName}>
    <formattedString>
      <span class="icon" text="save"/>
      <span>Speichern</span>
    </formattedString>
  </button>

  <Play {speed}/>
</stackLayout>

<script>
  import {onMount} from 'svelte'
  import {prompt} from '@nativescript/core/ui/dialogs';
  import {knownFolders} from '@nativescript/core/file-system';
  import {getString, getNumber, setNumber, setString} from '@nativescript/core/application-settings';

  import Speed from '../components/speed/Speed';
  import Play from '../components/play/Play';

  let name = '';
  let speed;
  let db;
  let activePreset = 0;
  let presets = [];
  let presetsFile;

  $: presetsSelectionList = presets.map((preset) => `${preset.name} – ${preset.bpm}bpm`);

  onMount(async () => {
    activePreset = getNumber('preset');
    const presetsDir = knownFolders.currentApp().getFolder('presets');
    presetsFile = presetsDir.getFile('presets.json');
    presets = JSON.parse(await presetsFile.readText());

    name = presets[activePreset].name;
    speed = presets[activePreset].speed;
  });

  function getSongName() {
    prompt({
    	title: 'Speichern',
    	message: 'Bitte gib einen Namen ein',
    	okButtonText: 'Speichern',
    	cancelButtonText: 'Abbrechen',
    	defaultText: '',
    }).then(res => {
    	name = res.text
      presets.push({
        name,
        bpm: speed
      });

      presetsFile.writeText(JSON.stringify(presets))

      setNumber('preset', presets.length - 1);
    });
  }
</script>

<style>
  .name {
    font-weight: bold;
    text-align: center;
    padding: 40px 60px;
    background-color: rgb(172, 172, 172);
  }
</style>
