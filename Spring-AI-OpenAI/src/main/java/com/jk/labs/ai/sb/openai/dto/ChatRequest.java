package com.jk.labs.ai.sb.openai.dto;

import lombok.Data;

@Data
public class ChatRequest {

    private String userMessage;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
