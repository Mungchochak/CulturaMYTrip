package org.example.jobby.Controller;


import java.io.BufferedReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;  // 需要添加这个import

public class DeepSeekChat {
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



    public static String callDeepSeekAPI(String conversation,String systemPrompt) throws IOException {
        // 1. 构建请求JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "deepseek-reasoner");  // 指定模型


        // 构建消息数组
        JSONArray messages = new JSONArray();  // 修正：使用JSONArray而不是JSONObject[]
        JSONObject userMessage = new JSONObject();

        // system prompt
        messages.put(new JSONObject()
                .put("role", "system")
                .put("content", systemPrompt));

        // user input
        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", conversation));

        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);  // 控制回答随机性
        requestBody.put("max_tokens", 2000);  // 限制响应长度

        // 2. 创建HTTP连接
        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);  // 5秒连接超时
        connection.setReadTimeout(80000);    // 15秒读取超时

        // 3. 发送请求数据
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 4. 处理响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // 5. 解析JSON响应
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } finally {
            connection.disconnect();  // 确保连接关闭
        }
    }

}