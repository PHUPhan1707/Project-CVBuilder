package CVHandle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CVData {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String major;
    private BufferedImage avatar;
    private List<String> achievements;
    private List<String> achievementDescriptions;
    private List<String> hobbies;
    private List<String> hobbyDescriptions;
    private List<String> education;
    private List<String> educationDescriptions;
    private List<String> experiences;
    private List<String> experienceDescriptions;
    private List<String> skills;
    private List<String> skillDescriptions;

    public CVData() {
        achievements = new ArrayList<>();
        achievementDescriptions = new ArrayList<>();
        hobbies = new ArrayList<>();
        hobbyDescriptions = new ArrayList<>();
        education = new ArrayList<>();
        educationDescriptions = new ArrayList<>();
        experiences = new ArrayList<>();
        experienceDescriptions = new ArrayList<>();
        skills = new ArrayList<>();
        skillDescriptions = new ArrayList<>();
    }

    // Getters and setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void addAchievement(String achievement, String description) {
        achievements.add(achievement);
        achievementDescriptions.add(description);
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void addHobby(String hobby, String description) {
        hobbies.add(hobby);
        hobbyDescriptions.add(description);
    }

    public List<String> getEducation() {
        return education;
    }

    public void addEducation(String education, String description) {
        this.education.add(education);
        educationDescriptions.add(description);
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public void addExperience(String experience, String description) {
        experiences.add(experience);
        experienceDescriptions.add(description);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void addSkill(String skill, String description) {
        skills.add(skill);
        skillDescriptions.add(description);
    }

    public List<String> getAchievementDescriptions() {
        return achievementDescriptions;
    }

    public List<String> getHobbyDescriptions() {
        return hobbyDescriptions;
    }

    public List<String> getEducationDescriptions() {
        return educationDescriptions;
    }

    public List<String> getExperienceDescriptions() {
        return experienceDescriptions;
    }

    public List<String> getSkillDescriptions() {
        return skillDescriptions;
    }
}