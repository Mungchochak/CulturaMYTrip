package org.example.jobby.Model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//CompanyInfoDao

public class FileData implements DataDao<Data>{
    @Override
    public void save(Data data, String filePath) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Name:" + data.getName() + "\n");
            writer.write("Email:" + data.getEmail() + "\n");
            writer.write("Industry:" + data.getIndustry() + "\n");
            writer.write("SSM No:" + data.getSsmNo() + "\n");
            writer.write("Address:" + data.getAddress() + "\n");
            writer.write("Contact:" + data.getContact() + "\n");
            writer.write("AdditionalInfo:" + data.getAdditionalInfo() + "\n");
        }
    }

    @Override
    public Data load(String filePath) throws Exception {
        Data data = new Data();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) data.setName(line.substring(5));
                else if (line.startsWith("Email:")) data.setEmail(line.substring(6));
                else if (line.startsWith("Industry:")) data.setIndustry(line.substring(9));
                else if (line.startsWith("SSM No:")) data.setSsmNo(line.substring(7));
                else if (line.startsWith("Address:")) data.setAddress(line.substring(8));
                else if (line.startsWith("Contact:")) data.setContact(line.substring(8));
                else if (line.startsWith("AdditionalInfo:")) data.setAdditionalInfo(line.substring(15));
            }
        }
        return data;
    }


    public static List<String> extractCompanyValues() throws IOException {

        List<String> values = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Text/Company_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int colonIndex = line.indexOf(':');
                if (colonIndex != -1 && colonIndex < line.length() - 1) {
                    String value = line.substring(colonIndex + 1).trim();
                    values.add(value);
                }
            }
        }

        return values;
    }

    public static void initializeFiles(String[] fileNames) {
        String folderPath = "src/main/resources/Text"; // 指定文件夹路径

        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();  // 创建多层文件夹
            if (created) {
                System.out.println("✅ Folder created: " + folderPath);
            } else {
                System.err.println("❌ Failed to create folder.");
            }
        } else {
            System.out.println("📁 Folder already exists: " + folderPath);
        }

        for (String fileName : fileNames) {
            File file = new File(folderPath+ File.separator+fileName);
            try {
                if (file.exists()) {
                    System.out.println(fileName + " File already exists");
                } else {
                    if (file.createNewFile()) {
                        System.out.println(fileName + " File created");

                        try (FileWriter writer = new FileWriter(file)) {
                            writer.write("");
                        }
                    } else {
                        System.out.println("Unable to create file: " + fileName);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: " + fileName);
                e.printStackTrace();
            }
        }
    }
}


