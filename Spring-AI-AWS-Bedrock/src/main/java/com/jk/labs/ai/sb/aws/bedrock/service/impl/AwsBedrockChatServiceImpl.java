package com.jk.labs.ai.sb.aws.bedrock.service.impl;

import com.jk.labs.ai.sb.aws.bedrock.service.AwsBedrockChatService;
import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsBedrockChatServiceImpl implements AwsBedrockChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsBedrockChatServiceImpl.class);

    public AwsBedrockChatServiceImpl() {
        LOGGER.info("Ollama Chat Service created");
    }

    @Autowired
    private ChatClient chatClient;

    @Override
    public void executeUserMessage(ChatRequest request, ChatResponse response) {
        LOGGER.info("STARTED executeUserMessage");

        String ollmResponse = chatClient.prompt(request.getUserMessage()).call().content();
        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", ollmResponse);

        LOGGER.info("COMPLETED executeUserMessage ollmResponse {}", ollmResponse);
    }
}
