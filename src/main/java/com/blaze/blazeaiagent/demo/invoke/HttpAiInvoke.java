package com.blaze.blazeaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 *  http调用AI
 */
public class HttpAiInvoke {
    public static void main(String[] args) {
        String apiKey = TestApiKey.API_KEY;

        // 构建请求JSON
        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "qwen-plus");

        JSONObject input = new JSONObject();
        JSONObject systemMessage = new JSONObject();
        systemMessage.set("role", "system");
        systemMessage.set("content", "You are a helpful assistant.");

        JSONObject userMessage = new JSONObject();
        userMessage.set("role", "user");
        userMessage.set("content", "你是谁？");

        input.set("messages", JSONUtil.createArray().put(systemMessage).put(userMessage));
        requestBody.set("input", input);

        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        requestBody.set("parameters", parameters);

        // 发送请求
        String response = HttpRequest.post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .execute()
                .body();

        System.out.println(response);
    }
}
