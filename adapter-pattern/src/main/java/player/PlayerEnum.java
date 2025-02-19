package player;

import adapter.Mp3Adapter;
import adapter.WavAdapter;
import adapter.PlayerAdpter;

import java.util.Arrays;

public enum PlayerEnum {

    MP3("MP3", Mp3Adapter.class),
    WAV("WAV", WavAdapter.class);

    private String audioType;
    private Class<? extends PlayerAdpter> playerClass;


    PlayerEnum(String audioType, Class<? extends PlayerAdpter> playerClass) {
        this.audioType = audioType;
        this.playerClass = playerClass;
    }

    public static PlayerAdpter getAdapterForFile(String fileName) {
        String extension = getFileExtension(fileName).toUpperCase();
        return Arrays.stream(PlayerEnum.values())
                .filter(playerEnum -> playerEnum.audioType.equalsIgnoreCase(extension))
                .findFirst()
                .map(playerEnum -> {
                    try {
                        return playerEnum.playerClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to create adapter for file: " + fileName, e);
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("Unsupported audio type: " + extension));
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        }
    }
}
