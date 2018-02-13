package gui;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerPanel extends JPanel {

    private File vlcInstallPath = new File("D:\\Program Files\\VideoLAN\\VLC");

    private EmbeddedMediaPlayer player;
    private com.google.zxing.Result result = null;
    private BufferedImage image = null;

    public PlayerPanel() {

        NativeLibrary.addSearchPath("libvlc", vlcInstallPath.getAbsolutePath());
        EmbeddedMediaPlayerComponent videoCanvas = new EmbeddedMediaPlayerComponent();
        this.setSize(new Dimension(200, 200));
        this.setMaximumSize(new Dimension(200, 200));
        this.setPreferredSize(new Dimension(200, 200));
        this.setMinimumSize(new Dimension(200, 200));
        this.setLayout(new BorderLayout());
        this.add(videoCanvas, BorderLayout.CENTER);
        this.player = videoCanvas.getMediaPlayer();
        JPanel controlsPanel = new JPanel();
        JButton pauseButton = new JButton("Pause");
        controlsPanel.add(pauseButton);
        JButton playButton = new JButton("Play");
        controlsPanel.add(playButton);
        this.add(controlsPanel, BorderLayout.SOUTH);

        pauseButton.addActionListener(e -> videoCanvas.getMediaPlayer().pause());
        playButton.addActionListener(e -> videoCanvas.getMediaPlayer().play());


    }

    public void scanQR() {


        image = player.getVideoSurfaceContents();
        System.out.println("test");
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            // fall thru, it means there is no QR code in image
        }
        if (result != null) {
            JOptionPane.showMessageDialog(null, "QR code data is: " + result.getText());
            System.out.println("QR code data is: " + result.getText());
        }
        //for test only
        /*
        File outfile = new File("image.jpg");
        try {
            ImageIO.write(image, "jpg", outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


    }

    /**
     * Add url to player
     */
    public void play(String media) {

        player.prepareMedia(media);
        player.parseMedia();
        player.play();

    }


    /**
     * Disable player
     */
    public void stop() {

        player.stop();

    }

}