import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JPanel controlsPanel = new JPanel();
        JButton pauseButton = new JButton("Pause");
        controlsPanel.add(pauseButton);
        JButton rewindButton = new JButton("Rewind");
        controlsPanel.add(rewindButton);
        JButton skipButton = new JButton("Skip");
        controlsPanel.add(skipButton);
        this.add(controlsPanel, BorderLayout.SOUTH);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoCanvas.getMediaPlayer().pause();
            }
        });
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoCanvas.getMediaPlayer().skip(-10000);
            }
        });
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoCanvas.getMediaPlayer().setFullScreen(true);

            }
        });

    }

    /**
     * Add url to player
     */
    public void play(String media) {

        player.prepareMedia(media);
        player.parseMedia();
        player.play();

    }

}