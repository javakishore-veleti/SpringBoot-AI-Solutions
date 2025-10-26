package com.jk.labs.ai.sb.openai.service.impl;

import com.jk.labs.ai.sb.openai.dto.ChatRequest;
import com.jk.labs.ai.sb.openai.dto.ChatResponse;
import com.jk.labs.ai.sb.openai.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public void executeUserMessage(ChatRequest request, ChatResponse response) {
        LOGGER.info("STARTED executeUserMessage");

        LOGGER.info("COMPLETED executeUserMessage");
    }
}
