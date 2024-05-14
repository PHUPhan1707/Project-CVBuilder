package MenuApp.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import net.coobird.thumbnailator.makers.FixedSizeThumbnailMaker;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;

public class PicturePreviewDialogue extends JDialog {
    private ImageIcon imageIcon;

    public PicturePreviewDialogue(JFrame parent, ImageIcon imageIcon) {
        super(parent, true);
        this.imageIcon = imageIcon;
        initComponents();
    }

    private void initComponents() {
        setUndecorated(true);
        setResizable(false);
        setSize(500, 700);  // Set the fixed size of the frame
        setLocationRelativeTo(getParent());

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 0)); // Transparent background
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Convert ImageIcon to BufferedImage
        Image originalImage = imageIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        // Resize the BufferedImage with Thumbnailator
        BufferedImage scaledImage = scaleThumbnailator(bufferedImage, 500, 700);

        // Convert BufferedImage back to ImageIcon
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        contentPane.add(imageLabel, BorderLayout.CENTER);

        // Close the dialog when clicked anywhere
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }

    public static BufferedImage scaleThumbnailator(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            Resizer resizer = DefaultResizerFactory.getInstance().getResizer(
                    new Dimension(imageToScale.getWidth(), imageToScale.getHeight()),
                    new Dimension(dWidth, dHeight));

            scaledImage = new FixedSizeThumbnailMaker(dWidth, dHeight, false, true)
                    .resizer(resizer)
                    .make(imageToScale);
        }
        return scaledImage;
    }
}