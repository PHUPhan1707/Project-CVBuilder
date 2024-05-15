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

        // Scale the cv img
        BufferedImage scaledImage = scaleImage(cvTemplate, MAX_WIDTH, MAX_HEIGHT);

        // Create a BufferedImage to hold the combined image
        BufferedImage combinedImage = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics object to draw onto the combined image
        Graphics2D g = combinedImage.createGraphics();

        // Draw the scaled CV template image onto the combined image
        g.drawImage(scaledImage, 0, 0, null);

        // Fetch the CV data from the database
        CVData cvData = fetchCVData(cvTemplate);

        // Render the CV data onto the combined image
        renderCVData(g, cvData);

        // Dispose the Graphics object
        g.dispose();

        // Create a JLabel to hold the combined image
        JLabel label = new JLabel(new ImageIcon(combinedImage));

        // Create a JFrame to display the CV
        JFrame frame = new JFrame("CV Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setResizable(false);
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
                    "SELECT FName, LName, Address, PhoneNumber, Email, AvatarPicture, MajorName, " +
                            "AchName, AchiDescription, HobbName, HobbDescription, EduName, EduDescription, ExName, ExDescription, SkillName, SkillDescription FROM information " +

                            "LEFT JOIN information_phonenumber ON information.InfoID = information_phonenumber.InfoID " +
                            "LEFT JOIN information_email ON information.InfoID = information_email.InfoID " +
                            "LEFT JOIN major ON information.UserID = major.UserID " +
                            "LEFT JOIN achievement ON information.UserID = achievement.UserID " +
                            "LEFT JOIN hobbies ON information.UserID = hobbies.UserID " +
                            "LEFT JOIN education ON information.UserID = education.UserID " +
                            "LEFT JOIN workexperience ON information.UserID = workexperience.UserID " +
                            "LEFT JOIN skill ON information.UserID = skill.UserID"
            );

            if (resultSet.next()) {
                // Retrieve info from information table
                cvData.setFullName(resultSet.getString("FName") + " " + resultSet.getString("LName"));

                cvData.setAddress(resultSet.getString("Address"));
                cvData.setPhoneNumber(resultSet.getString("PhoneNumber"));
                cvData.setEmail(resultSet.getString("Email"));

                cvData.setMajor(resultSet.getString("MajorName"));

                cvData.setAchievement(resultSet.getString("AchName"));
                cvData.setAchievementDescription(resultSet.getString("AchiDescription"));

                cvData.setHobby(resultSet.getString("HobbName"));
                cvData.setHobbyDescription(resultSet.getString("HobbDescription"));

                cvData.setEducation(resultSet.getString("EduName"));
                cvData.setEducationDescription(resultSet.getString("EduDescription"));

                cvData.setExperience(resultSet.getString("ExName"));
                cvData.setExperienceDescription(resultSet.getString("ExDescription"));

                cvData.setSkillName(resultSet.getString("SkillName"));
                cvData.setSkillDescription(resultSet.getString("SkillDescription"));

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

        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.white);
        g.drawString(cvData.getFullName(), 10, 200); //Name


        Font majorFont = new Font("Arial", Font.PLAIN, 15);
        g.setFont(majorFont);
        g.drawString(cvData.getMajor(), 30, 235); // Major


        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setColor(Color.white);

        // Contact
        g.drawString(cvData.getAddress(), 40, 504); //Address
        String phoneNumber = cvData.getPhoneNumber() != null ? cvData.getPhoneNumber() : "PhoneNum Not Provided"; //phone
        g.drawString(phoneNumber, 40, 530);
        String email = cvData.getEmail() != null ? cvData.getEmail() : "Email Not Provided"; //email
        g.drawString(email, 40, 556);

        // Avatar image
        BufferedImage avatarImage = cvData.getAvatar();
        if (avatarImage != null) {
            // Avatar position
            g.drawImage(avatarImage, 60, 60, null); // Adjust the position as needed
        } else {
            // If avatar image is not available, draw a square
            g.setColor(Color.cyan);
            g.fillRect(0, 0, 178, 164);
        }


        // Render in black text
        g.setColor(Color.black);

        String achievement = cvData.getAchievement() != null ? cvData.getAchievement() : "Achieve Name Not Provided";
        String achievementDescription = cvData.getAchievementDescription() != null ? cvData.getAchievementDescription() : "Achieve Description Not Provided";
        g.drawString("Achie: " + achievement, 220, 613); // Achievement
        g.drawString("Description: " + achievementDescription, 220, 633); // Achievement Description

        String hobby = cvData.getHobby() != null ? cvData.getHobby() : "Hobby Name Not Provided";
        String hobbyDescription = cvData.getHobbyDescription() != null ? cvData.getHobbyDescription() : "Hobby Description Not Provided";
        g.drawString("Hobby: " + hobby, 220, 685); // Hobby
        g.drawString("Description: " + hobbyDescription, 220, 705); // Hobby Description

        String education = cvData.getEducation() != null ? cvData.getEducation() : "Edu Name Not Provided";
        String educationDescription = cvData.getEducationDescription() != null ? cvData.getEducationDescription() : "Edu Description Not Provided";
        g.drawString("Edu: " + education, 220, 270); // Education
        g.drawString("Description: " + educationDescription, 220, 290); // Education Description

        String experience = cvData.getExperience() != null ? cvData.getExperience() : "Exp Name Not Provided";
        String experienceDescription = cvData.getExperienceDescription() != null ? cvData.getExperienceDescription() : "Exp Description Not Provided";
        g.drawString("Exp: " + experience, 220, 60); // Experience
        g.drawString("Description: " + experienceDescription, 220, 80); // Experience Description

        String skillName = cvData.getSkillName() != null ? cvData.getSkillName() : "Skill Name Not Provided";
        String skillDescription = cvData.getSkillDescription() != null ? cvData.getSkillDescription() : "Skill Description Not Provided";
        g.drawString("Skill: " + skillName, 220, 480);
        g.drawString("Description: " + skillDescription, 220, 500);

    }
}
