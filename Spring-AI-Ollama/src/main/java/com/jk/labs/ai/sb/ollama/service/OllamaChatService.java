package com.jk.labs.ai.sb.ollama.service;

import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;

public interface OllamaChatService {

    void executeUserMessage(ChatRequest request, ChatResponse response);
}
