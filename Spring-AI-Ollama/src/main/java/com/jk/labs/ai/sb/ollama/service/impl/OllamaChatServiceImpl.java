package com.jk.labs.ai.sb.ollama.service.impl;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;
import com.jk.labs.ai.sb.ollama.service.OllamaChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatServiceImpl implements OllamaChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OllamaChatServiceImpl.class);

    public OllamaChatServiceImpl() {
        LOGGER.info("Ollama Chat Service created");
    }

    @Autowired
    private ChatClient chatClient;

    @Override
    public void executeUserMessage(AppChatRequest request, AppChatResponse response) {
        LOGGER.info("STARTED executeUserMessage");

        String ollmResponse = chatClient.prompt(request.getUserMessage()).call().content();
        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", ollmResponse);

        LOGGER.info("COMPLETED executeUserMessage ollmResponse {}", ollmResponse);
    }
}
