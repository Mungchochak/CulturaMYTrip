package org.example.jobby.Model;

public class PositionDesc {

    private String position;
    private String workingMode;
    private String minSalary;
    private String maxSalary;
    private String description;

    public PositionDesc() {}

    public PositionDesc( String position, String workingMode, String minSalary,String maxSalary, String description) {
        this.position = position;
        this.workingMode = workingMode;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
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

