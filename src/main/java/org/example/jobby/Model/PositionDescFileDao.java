package org.example.jobby.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PositionDescFileDao implements DataDao<PositionDesc> {
    @Override
    public void save(PositionDesc data, String filePath) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Position:" + data.getPosition() + " (" + data.getWorkingMode() + ") - Min Salary: " + data.getMinSalary() + " Max Salary: " + data.getMaxSalary() + "\n");
            writer.write("Description:" + data.getDescription() + "\n");
            writer.write("====\n"); // delimiter
        }
    }


    @Override
    public PositionDesc load(String filePath) throws Exception {
        // Loads the LAST position description in the file
        PositionDesc desc = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            PositionDesc temp = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Position:")) {
                    temp = new PositionDesc();
                    temp.setPosition(line.substring(9).trim());
                } else if (line.startsWith("Description:") && temp != null) {
                    temp.setDescription(line.substring(12).trim());
                    desc = temp; // set last fully read position
                }
            }
        }
        return desc;
    }



    // Loads all Position:... lines, to show/delete
    public List<String> loadPositionLines(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Position:")) {
                    result.add(line.trim()); // full line, for display & unique delete
                }
            }
        }
        return result;
    }

    public void deleteByFullLine(String filePath, String fullLineToDelete) throws Exception {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipBlock = false;
            boolean blockDeleted = false; // Only skip ONE matching block!
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Position:") && line.trim().equals(fullLineToDelete.trim()) && !blockDeleted) {
                    skipBlock = true;
                    blockDeleted = true;
                    continue; // don't add this Position: line
                }
                if (skipBlock) {
                    if (line.equals("====")) {
                        skipBlock = false; // end of the block, resume writing
                    }
                    continue; // skip all lines until ====
                }
                lines.add(line);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        }
    }

}
