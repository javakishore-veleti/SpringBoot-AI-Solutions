package com.jk.labs.ai.sb.ollama.service;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;

public interface OllamaChatService {

    void executeUserMessage(AppChatRequest request, AppChatResponse response);
}
