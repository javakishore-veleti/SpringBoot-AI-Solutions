package com.jk.labs.ai.sb.openai.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChatResponse {

    private Map<String, Object> responses;

    public ChatResponse() {
        responses = new HashMap<>();
    }

}
