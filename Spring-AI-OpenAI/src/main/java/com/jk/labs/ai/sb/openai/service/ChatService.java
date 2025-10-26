package com.jk.labs.ai.sb.openai.service;

import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;

public interface ChatService {

    void executeUserMessage(ChatRequest request, ChatResponse response);
}
