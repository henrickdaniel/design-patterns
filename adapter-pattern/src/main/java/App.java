import adapter.PlayerAdpter;
import player.PlayerEnum;

public class App {

    public static void main(String[] args) {
        String file = "Os Paralamas Do Sucesso - Aonde Quer Que Eu Vá.wav";
        PlayerAdpter playerAdpter = PlayerEnum.getAdapterForFile(file);
        playerAdpter.play(file);
    }
}
