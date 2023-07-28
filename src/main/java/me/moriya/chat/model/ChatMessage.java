package me.moriya.chat.model;

import java.util.Objects;

class ChatMessage {

    private String role;
    private String content;

    private ChatMessage(String content) {
        this("user", content);
    }

    private ChatMessage(String role, String content) {
        Objects.requireNonNull(role, "You must provide a role ['system', 'assistant', 'user', 'function']");
        Objects.requireNonNull(content, "You must provide a message");
        this.role = role;
        this.content = content;
    }

    public static ChatMessage of(String content) {
        return new ChatMessage(content);
    }

    public static ChatMessage of(String role, String content) {
        return new ChatMessage(role, content);
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
