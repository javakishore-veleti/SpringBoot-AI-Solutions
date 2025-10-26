package com.jk.labs.ai.sb.openai.service.impl;

import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;
import com.jk.labs.ai.sb.openai.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    private ChatClient chatClient;

    @Override
    public void executeUserMessage(ChatRequest request, ChatResponse response) {
        LOGGER.info("STARTED executeUserMessage");

        String llmResponse = chatClient.prompt(request.getUserMessage()).
                advisors(
                        List.of(
                                new SimpleLoggerAdvisor(),
                                new SafeGuardAdvisor(List.of("Ford", "BMW"))))
                .call().content();
        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", llmResponse);

        LOGGER.info("COMPLETED executeUserMessage llmResponse {}", llmResponse);
    }
}
