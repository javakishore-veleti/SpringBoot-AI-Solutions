package com.jk.labs.ai.sb.common.dto;

import lombok.Data;

@Data
public class AppChatRequest {

    private String userMessage;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
