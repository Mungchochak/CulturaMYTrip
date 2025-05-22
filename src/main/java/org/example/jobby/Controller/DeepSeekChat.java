package org.example.jobby.Controller;


import java.io.BufferedReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;  // 需要添加这个import

public class DeepSeekChat {

    public static boolean FaildConnection = false;
    // DeepSeek API端点
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";
    // 替换为你的实际API密钥
    private static final String API_KEY = "sk-56bbdb6baa4749f493ac695536710866";

//    public static void DeepSeekSum(){
//        Scanner scanner = new Scanner(System.in);
//
//
//        // 使用StringBuilder高效存储对话历史
//        StringBuilder conversationHistory = new StringBuilder();
//
//        while (true) {
//            System.out.print("You: ");
//            String userInput = scanner.nextLine();
//
//            // 检查退出命令
//            if ("exit".equalsIgnoreCase(userInput)) {
//                break;
//            }
//
//            try {
//                // 添加当前用户输入到历史记录
//                conversationHistory.append("用户: ").append(userInput).append("\n");
//
//                // 调用API获取AI回复
//                String aiResponse = callDeepSeekAPI(conversationHistory.toString());
//
//                // 添加AI回复到历史记录
//                conversationHistory.append("AI: ").append(aiResponse).append("\n");
//
//                // 打印AI回复
//                System.out.println("AI: " + aiResponse);
//            } catch (IOException e) {
//                System.out.println("调用API时出错: " + e.getMessage());
//                e.printStackTrace();  // 打印完整错误堆栈
//            }
//        }
//
//        // 清理资源
//        scanner.close();
//        System.out.println("聊天结束");
//    }



    public static String callDeepSeekAPI(String conversation, String systemPrompt) {
        try {
            // 构建请求
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "deepseek-chat");

            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));
            messages.put(new JSONObject().put("role", "user").put("content", conversation));
            requestBody.put("messages", messages);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 2000);

            // 建立连接
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(80000);

            // 发送请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 读取响应
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
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