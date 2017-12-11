import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PlayerPanel extends JPanel {

    private File vlcInstallPath = new File("D:\\Program Files\\VideoLAN\\VLC");
    private EmbeddedMediaPlayer player;

    public PlayerPanel() {
        NativeLibrary.addSearchPath("libvlc", vlcInstallPath.getAbsolutePath());
        EmbeddedMediaPlayerComponent videoCanvas = new EmbeddedMediaPlayerComponent();
        this.setLayout(new BorderLayout());
        this.add(videoCanvas, BorderLayout.CENTER);
        this.player = videoCanvas.getMediaPlayer();

    }

    public void play(String media) {

        player.prepareMedia(media);
        player.parseMedia();
        player.play();
    }

    public void stop() {

        player.stop();

    }
}