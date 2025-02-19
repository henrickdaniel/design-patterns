package adapter;

import player.WavAudioPlayer;

public class WavAdapter implements PlayerAdpter {

    @Override
    public void play(String fileName) {
        WavAudioPlayer wavAudioPlayer = new WavAudioPlayer();
        wavAudioPlayer.playWav(fileName);
    }
}
