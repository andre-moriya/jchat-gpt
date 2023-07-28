package me.moriya.chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatCompletionRequest {

    private String model;

    private List<ChatMessage> messages = new ArrayList<>();

    public ChatCompletionRequest() {
        this("gpt-3.5-turbo");
    }

    public ChatCompletionRequest(String model) {
        Objects.requireNonNull(model, "You must provide a model parameter");
        this.model = model;
    }

    public void addMessage(String content) {
        messages.add(ChatMessage.of(content));
    }

    public void addMessage(String role, String content) {
        messages.add(ChatMessage.of(role, content));
    }

    public String getModel() {
        return model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
}
