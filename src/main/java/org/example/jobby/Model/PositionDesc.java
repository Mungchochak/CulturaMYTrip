package org.example.jobby.Model;

public class PositionDesc {

    private String position;
    private String workingMode;
    private String minSalary;
    private String maxSalary;
    private String description;
    private String skillMatch;
    private String educationalbackground;
    private String workexperience;


    public PositionDesc() {}

    public PositionDesc( String position, String workingMode, String minSalary,String maxSalary, String description, String skillMatch, String educationalbackground, String workexperience) {
        this.position = position;
        this.workingMode = workingMode;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
        this.skillMatch = skillMatch;
        this.educationalbackground = educationalbackground;
        this.workexperience = workexperience;
    }
    public String getWorkexperience() {
        return workexperience;
    }

    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }

    public String getEducationalbackground() {
        return educationalbackground;
    }

    public void setEducationalbackground(String educationalbackground) {
        this.educationalbackground = educationalbackground;
    }

    public String getSkillMatch() {
        return skillMatch;
    }

    public void setSkillMatch(String skillMatch) {
        this.skillMatch = skillMatch;
    }





    // Getters and setters for all fields

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getWorkingMode() { return workingMode; }
    public void setWorkingMode(String workingMode) { this.workingMode = workingMode; }
    public String getMinSalary() { return minSalary; }
    public void setMinSalary(String minSalary) { this.minSalary = minSalary; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getMaxSalary() { return maxSalary; }
    public void setMaxSalary(String maxSalary) { this.maxSalary = maxSalary; }
    }

