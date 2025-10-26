package com.jk.labs.ai.sb.common.dto;

import lombok.Data;

@Data
public class AppChatRequest {

    private String userMessage;
    private String userName = "anonymous";

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
