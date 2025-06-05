package org.example.jobby.Controller;


import java.io.BufferedReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;
import org.json.JSONArray;

public class DeepSeekChat {

    public static boolean FaildConnection = false;

    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    private static final String API_KEY = "sk-56bbdb6baa4749f493ac695536710866";

    public static JSONArray MessagesHistory = new JSONArray();




    public static String callDeepSeekAPI(String conversation, String systemPrompt) {
        try {

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "deepseek-chat");

            JSONArray messages = new JSONArray();


            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));


            messages.put(new JSONObject().put("role", "user").put("content", conversation));


            requestBody.put("messages", messages);
            requestBody.put("temperature", 0.3);
            requestBody.put("max_tokens", 8000);


            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(80000);


            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                JSONObject jsonResponse = new JSONObject(response.toString());


                String content = jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");


                MessagesHistory.put(new JSONObject().put("role", "user").put("content", conversation));
                MessagesHistory.put(new JSONObject().put("role", "assistant").put("content", content));
                messages.put(new JSONObject().put("role", "assistant").put("content", content));



                System.out.println(messages);


                return content;

            } finally {
                connection.disconnect();
            }

        } catch (IOException e) {

            System.err.println("❌ DeepSeek connection failed: " + e.getMessage());
            FaildConnection = true;
            return " ";
        }
    }




}


