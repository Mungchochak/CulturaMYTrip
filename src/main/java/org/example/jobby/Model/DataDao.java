package org.example.jobby.Model;

    public interface DataDao {
        void save(Data data, String filePath) throws Exception;
        Data load(String filePath) throws Exception;
    }

