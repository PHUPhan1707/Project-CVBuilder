package CVHandle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class CVHandler2 {
        private static final String CV_TEMPLATE_PATH = "png/CV_2 blank.png";
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
            CVData cvData = fetchCVData();

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

        private CVData fetchCVData() {
            CVData cvData = new CVData();

            // Fetch data from the database and populate cvData object
            try (Connection connection = DatabaseConnector.connect();
                 Statement statement = connection.createStatement()) {

                // Execute SQL query to fetch user information
                ResultSet resultSet = statement.executeQuery(
                        "SELECT FName, LName, Address, PhoneNumber, Email, AvatarPicture, MajorName, " +
                                "AchName, AchiDescription, HobbName, HobbDescription, EduName, EduDescription, " +
                                "ExName, ExDescription, SkillName, SkillDescription FROM information " +

                                "LEFT JOIN information_phonenumber ON information.InfoID = information_phonenumber.InfoID " +
                                "LEFT JOIN information_email ON information.InfoID = information_email.InfoID " +
                                "LEFT JOIN major ON information.UserID = major.UserID " +
                                "LEFT JOIN achievement ON information.UserID = achievement.UserID " +
                                "LEFT JOIN hobbies ON information.UserID = hobbies.UserID " +
                                "LEFT JOIN education ON information.UserID = education.UserID " +
                                "LEFT JOIN workexperience ON information.UserID = workexperience.UserID " +
                                "LEFT JOIN skill ON information.UserID = skill.UserID"
                );

                while (resultSet.next()) {
                    // Retrieve info from information table
                    cvData.setFullName(resultSet.getString("FName") + " " + resultSet.getString("LName"));

                    cvData.setAddress(resultSet.getString("Address"));
                    cvData.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    cvData.setEmail(resultSet.getString("Email"));

                    cvData.setMajor(resultSet.getString("MajorName"));

                    cvData.addAchievement(resultSet.getString("AchName"), resultSet.getString("AchiDescription"));
                    cvData.addHobby(resultSet.getString("HobbName"), resultSet.getString("HobbDescription"));
                    cvData.addEducation(resultSet.getString("EduName"), resultSet.getString("EduDescription"));
                    cvData.addExperience(resultSet.getString("ExName"), resultSet.getString("ExDescription"));
                    cvData.addSkill(resultSet.getString("SkillName"), resultSet.getString("SkillDescription"));

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
            g.setColor(Color.BLACK);
            String fullName = cvData.getFullName() != null ? cvData.getFullName() : "Name Not Provided"; //Name
            g.drawString(fullName, 210, 60);

            Font majorFont = new Font("Arial", Font.PLAIN, 15);
            g.setFont(majorFont);
            String major = cvData.getMajor() != null ? cvData.getMajor() : "Major Not Provided";
            g.drawString(major, 350, 90); // Major

            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.setColor(Color.black);

            // Contact
            String address = cvData.getAddress() != null ? cvData.getAddress() : "Address Not Provided";
            g.drawString(address, 60, 286); //Address
            String phoneNumber = cvData.getPhoneNumber() != null ? cvData.getPhoneNumber() : "PhoneNum Not Provided"; //phone
            g.drawString(phoneNumber, 60, 268);
            String email = cvData.getEmail() != null ? cvData.getEmail() : "Email Not Provided"; //email
            g.drawString(email, 60, 304);

            // Avatar image
            BufferedImage avatarImage = cvData.getAvatar();
            if (avatarImage != null) {
                // Avatar position
                g.drawImage(avatarImage, 60, 60, null); // Adjust the position as needed
            } else {
                // If avatar image is not available, draw a square
                g.setColor(Color.white);
                g.fillRect(18, 10, 180, 168);
            }


            g.setColor(Color.black);

            int y = 606; // Starting y-coordinate
            if (cvData.getAchievements().isEmpty()) { // If achievements list is empty
                g.drawString("Achievement: Not provided", 38, y);
                g.drawString("Description: Not provided", 38, y + 18);
            } else {
                for (int i = 0; i < Math.min(1, cvData.getAchievements().size()); i++) { // maximum 1
                    String achievement = cvData.getAchievements().get(i);
                    String achievementDescription = cvData.getAchievementDescriptions().get(i);
                    g.drawString("Achievement: " + achievement, 38, y); // Achievement
                    g.drawString("Description: " + achievementDescription, 38, y + 18); // Achievement Description
                    y += 36; // Move to the next entry
                }
            }

            // Render hobbies
            y = 606; // Starting y-coordinate
            if (cvData.getHobbies().isEmpty()) { // If hobbies list is empty
                g.drawString("Hobby: Not provided", 224, y);
                g.drawString("Description: Not provided", 224, y + 18);
            } else {
                for (int i = 0; i < Math.min(1, cvData.getHobbies().size()); i++) { // maximum 1
                    String hobby = cvData.getHobbies().get(i);
                    String hobbyDescription = cvData.getHobbyDescriptions().get(i);
                    g.drawString("Hobby: " + hobby, 224, y); // Hobby
                    g.drawString("Description: " + hobbyDescription, 224, y + 18); // Hobby Description
                    y += 36; // Move to the next entry
                }
            }

            // Render education
            y = 275; // Starting y-coordinate
            for (int i = 0; i < Math.min(3, cvData.getEducation().size()); i++) { // maximum 3
                String education = cvData.getEducation().get(i);
                String educationDescription = cvData.getEducationDescriptions().get(i);
                if (education == null || education.isEmpty()) {
                    education = "Not provided";
                }
                if (educationDescription == null || educationDescription.isEmpty()) {
                    educationDescription = "Not provided";
                }
                g.drawString("Education: " + education, 220, y); // Education
                g.drawString("Description: " + educationDescription, 220, y + 15); // Education Description
                y += 40; // Move to the next entry
            }

            // Render experiences
            y = 60; // Starting y-coordinate
            for (int i = 0; i < Math.min(3, cvData.getExperiences().size()); i++) { // maximum 3
                String experience = cvData.getExperiences().get(i);
                String experienceDescription = cvData.getExperienceDescriptions().get(i);
                if (experience == null || experience.isEmpty()) {
                    experience = "Not provided";
                }
                if (experienceDescription == null || experienceDescription.isEmpty()) {
                    experienceDescription = "Not provided";
                }
                g.drawString("Experience: " + experience, 220, y); // Experience
                g.drawString("Description: " + experienceDescription, 220, y + 15); // Experience Description
                y += 40; // Move to the next entry
            }

            // Render skills
            y = 475; // Starting y-coordinate
            for (int i = 0; i < Math.min(3, cvData.getSkills().size()); i++) { // maximum 3
                String skill = cvData.getSkills().get(i);
                String skillDescription = cvData.getSkillDescriptions().get(i);
                if (skill == null || skill.isEmpty()) {
                    skill = "Not provided";
                }
                if (skillDescription == null || skillDescription.isEmpty()) {
                    skillDescription = "Not provided";
                }
                g.drawString("Skill: " + skill, 220, y); // Skill
                g.drawString("Description: " + skillDescription, 220, y + 15); // Skill Description
                y += 35; // Move to the next entry
            }
        }
    }
