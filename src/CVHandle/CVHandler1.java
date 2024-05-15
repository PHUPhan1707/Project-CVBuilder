package CVHandle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

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

            // Execute SQL query to fetch user information
            ResultSet resultSet = statement.executeQuery(
                    "SELECT information.FName, information.LName, information.Address, " +
                            "information_phonenumber.PhoneNumber, information_email.Email, " +
                            "information.AvatarPicture, major.MajorName, achievement.AchName, " +
                            "hobbies.HobbName, education.EduName, workexperience.ExName " +
                            "FROM information " +
                            "LEFT JOIN information_phonenumber ON information.InfoID = information_phonenumber.InfoID " +
                            "LEFT JOIN information_email ON information.InfoID = information_email.InfoID " +
                            "LEFT JOIN major ON information.UserID = major.UserID " +
                            "LEFT JOIN achievement ON information.UserID = achievement.UserID " +
                            "LEFT JOIN hobbies ON information.UserID = hobbies.UserID " +
                            "LEFT JOIN education ON information.UserID = education.UserID " +
                            "LEFT JOIN workexperience ON information.UserID = workexperience.UserID"
            );

            if (resultSet.next()) {
                // Retrieve info from information table
                cvData.setFullName(resultSet.getString("FName") + " " + resultSet.getString("LName"));
                cvData.setAddress(resultSet.getString("Address"));
                cvData.setPhoneNumber(resultSet.getString("PhoneNumber"));
                cvData.setEmail(resultSet.getString("Email"));
                cvData.setMajor(resultSet.getString("MajorName"));
                cvData.setAchievement(resultSet.getString("AchName"));
                cvData.setHobby(resultSet.getString("HobbName"));
                cvData.setEducation(resultSet.getString("EduName"));
                cvData.setExperience(resultSet.getString("ExName"));

                // Retrieve avatar picture
                Blob avatarBlob = resultSet.getBlob("AvatarPicture");
                if (avatarBlob != null) {
                    try (InputStream inputStream = avatarBlob.getBinaryStream()) {
                        BufferedImage avatarImage = ImageIO.read(inputStream);
                        cvData.setAvatar(avatarImage); // Set avatar picture in CVData
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Handle the IOException
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException
        }

        return cvData;
    }


    private void renderCVData(Graphics g, CVData cvData) {
        // Render CV data onto the template

        g.setFont(new Font("Arial", Font.BOLD, 12));
        // Render white text
        g.setColor(Color.white);

        g.drawString(cvData.getFullName(), 10, 200); //Name

        g.drawString(cvData.getMajor(), 10, 100); // Major

        g.drawString(cvData.getAddress(), 10, 300); //Address

        String phoneNumber = cvData.getPhoneNumber() != null ? cvData.getPhoneNumber() : "Not Provided"; //phone
        g.drawString(phoneNumber, 10, 400);

        String email = cvData.getEmail() != null ? cvData.getEmail() : "Not Provided"; //email
        g.drawString(email, 10, 500);

        // Check if avatar image is available
        BufferedImage avatarImage = cvData.getAvatar();
        if (avatarImage != null) {
            // Avatar image position
            g.drawImage(avatarImage, 60, 60, null); // Adjust the position as needed
        } else {
            // If avatar image is not available, draw a square
            g.setColor(Color.yellow);
            g.fillRect(0, 0, 100, 100);
        }
        // Render in black text
        g.setColor(Color.black);

        String achievement = cvData.getAchievement() != null ? cvData.getAchievement() : "Not Provided";
        g.drawString(achievement, 300, 200); // Achievement

        String hobby = cvData.getHobby() != null ? cvData.getHobby() : "Not Provided";
        g.drawString(hobby, 300, 300); // Hobby

        String education = cvData.getEducation() != null ? cvData.getEducation() : "Not Provided";
        g.drawString(education, 300, 400); // Education

        String experience = cvData.getExperience() != null ? cvData.getExperience() : "Not Provided";
        g.drawString(experience, 300, 500); // Experience

    }
}
