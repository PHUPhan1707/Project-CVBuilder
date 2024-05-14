package CVHandle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CVHandler1 {
    private static final String CV_TEMPLATE_PATH = "png/CV_1 blank.png";
    private static final int MAX_WIDTH = 510; // Maximum width for scaling
    private static final int MAX_HEIGHT = 716; // Maximum height for scaling

    public void displayCV() {
        // Load your CV template image
        BufferedImage cvTemplate;
        try {
            cvTemplate = ImageIO.read(new File(CV_TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Scale the image to fit the screen while preserving aspect ratio
        BufferedImage scaledImage = scaleImage(cvTemplate, MAX_WIDTH, MAX_HEIGHT);

        // Create a JLabel to hold the scaled image
        JLabel label = new JLabel(new ImageIcon(scaledImage));

        // Create a JPanel to render the CV data
        JPanel cvDataPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Render CV data onto the panel
                renderCVData((Graphics2D) g, fetchCVData(cvTemplate));
            }
        };
        cvDataPanel.setOpaque(false); // Make the panel transparent

        // Add the cvDataPanel on top of the label
        label.setLayout(new OverlayLayout(label));
        label.add(cvDataPanel);

        // Create a JFrame to display the CV
        JFrame frame = new JFrame("CV Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label); // Add the label directly to the frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int maxWidth, int maxHeight) {
        int newWidth = Math.min(originalImage.getWidth(), maxWidth);
        int newHeight = Math.min(originalImage.getHeight(), maxHeight);

        // Create a new image with the scaled dimensions
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }

    private CVData fetchCVData(BufferedImage cvTemplate) {
        CVData cvData = new CVData();

        // Fetch data from the database and populate cvData object
        try (Connection connection = DatabaseConnector.connect();
             Statement statement = connection.createStatement()) {

            // Execute SQL queries to fetch data and populate cvData object
            ResultSet resultSet = statement.executeQuery("SELECT * FROM information");
            if (resultSet.next()) {
                cvData.setFullName(resultSet.getString("FName") + " " + resultSet.getString("LName"));
                cvData.setAddress(resultSet.getString("Address"));
                // Populate other fields similarly
            } else { //Test info
                // Generate random data if database returns null
                cvData.setFullName(RandomDataGenerator.generateRandomString(10));
                cvData.setAddress(RandomDataGenerator.generateRandomString(20));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cvData;
    }

    private void renderCVData(Graphics g, CVData cvData) {
        // Render CV data onto the template

        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawString("Name: " + cvData.getFullName(), 60, 200);
        g.drawString("Address: " + cvData.getAddress(), 100, 120);
        // Render other fields similarly
    }
}
