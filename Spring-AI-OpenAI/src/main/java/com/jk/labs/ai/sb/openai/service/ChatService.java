package com.jk.labs.ai.sb.openai.service;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;

public interface ChatService {

    void executeUserMessage(AppChatRequest request, AppChatResponse response);

    void executeUserMessageWithMemory(AppChatRequest request, AppChatResponse response);

    void executeUserMessageWithMemoryLimits(AppChatRequest request, AppChatResponse response);
}
