package org.example.jobby.Model;
import java.io.*;
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
}


