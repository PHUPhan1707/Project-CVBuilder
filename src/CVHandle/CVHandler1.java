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
    private static final int MAX_WIDTH = 765; // Maximum width for scaling
    private static final int MAX_HEIGHT = 1074; // Maximum height for scaling

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
        ImageIcon icon = new ImageIcon(cvTemplate.getScaledInstance(
                Math.min(MAX_WIDTH, cvTemplate.getWidth()),
                Math.min(MAX_HEIGHT, cvTemplate.getHeight()),
                Image.SCALE_SMOOTH));

        // Create a JLabel to hold the scaled image
        JLabel label = new JLabel(icon);

        // Create a JScrollPane to contain the label with scroll bars
        JScrollPane scrollPane = new JScrollPane(label) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Render CV data onto the template whenever the component is repainted
                Graphics2D g2d = (Graphics2D) g.create();
                renderCVData(g2d, fetchCVData(cvTemplate));
                g2d.dispose();
            }
        };
        scrollPane.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));

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

        // Overlay the cvDataPanel on top of the scroll pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(cvDataPanel, JLayeredPane.PALETTE_LAYER);

        // Create a JFrame to display the CV
        JFrame frame = new JFrame("CV Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane); // Add the scroll pane to the frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

        // Fetch CV data from the database
        CVData cvData = fetchCVData(cvTemplate);

        // Render CV data onto the template
        Graphics g = label.getGraphics();
        renderCVData(g, cvData);
    }

    private CVData fetchCVData(BufferedImage cvTemplate) {
        CVData cvData = new CVData();

        // Fetch data from the database and populate cvData object
        try (Connection connection = DatabaseConnector.connect();
             Statement statement = connection.createStatement()) {

            // Execute SQL queries to fetch data and populate cvData object
            // Example:
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
        // Example:
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.drawString("Name: " + cvData.getFullName(), 100, 100);
        g.drawString("Address: " + cvData.getAddress(), 100, 120);
        // Render other fields similarly
    }
}
