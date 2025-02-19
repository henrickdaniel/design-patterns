package adapter;

import player.Mp3AudioPlayer;

public class Mp3Adapter implements PlayerAdpter {



    @Override
    public void play(String fileName) {
        Mp3AudioPlayer mp3AudioPlayer = new Mp3AudioPlayer();
        mp3AudioPlayer.playMp3(fileName);
    }
}
