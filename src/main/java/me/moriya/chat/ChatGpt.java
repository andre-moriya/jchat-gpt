package me.moriya.chat;

import com.google.gson.Gson;
import me.moriya.chat.model.ChatCompletionRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ChatGpt {


    public static void main(String[] args) {
        try {
            ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
            chatCompletionRequest.addMessage("Hi! Show me the Java primitives types.");

            Properties prop = new Properties();
            prop.load(ChatGpt.class.getClassLoader().getResourceAsStream("application.properties"));

            var payload = HttpRequest
                    .BodyPublishers
                    .ofString(new Gson().toJson(chatCompletionRequest));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .header("Authorization", String.format("Bearer %s", prop.getProperty("chat.api_key")))
                    .header("Content-Type", "application/json")
                    .POST(payload)
                    .uri(new URI(prop.getProperty("chat.endpoint")))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.println(body);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
