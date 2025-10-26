package com.jk.labs.ai.sb.openai.service.impl;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;
import com.jk.labs.ai.sb.common.prompts_engg.advisors.JkAppTokenAuditUsageAdvisor;
import com.jk.labs.ai.sb.openai.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jk.labs.ai.sb.common.prompts_engg.memory.ChatMemoryChatClientConfig.BEAN_ID_CHAT_MEMORY_CLIENT;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    private ChatClient chatClient;

    @Autowired
    @Qualifier(BEAN_ID_CHAT_MEMORY_CLIENT)
    private ChatClient memoryChatClient;

    @Override
    public void executeUserMessage(AppChatRequest request, AppChatResponse response) {
        LOGGER.info("STARTED executeUserMessage");

        String llmResponse = chatClient.prompt(request.getUserMessage()).
                advisors(
                        List.of(
                                new SimpleLoggerAdvisor(),
                                new SafeGuardAdvisor(List.of("Ford", "BMW")),
                                new JkAppTokenAuditUsageAdvisor()))
                .call().content();
        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", llmResponse);

        LOGGER.info("COMPLETED executeUserMessage llmResponse {}", llmResponse);
    }

    @Override
    public void executeUserMessageWithMemory(AppChatRequest request, AppChatResponse response) {
        LOGGER.info("STARTED executeUserMessageWithMemory");

        String llmResponse = memoryChatClient.prompt(request.getUserMessage())
                .call().content();
        response.addResult("UserMessage", request.getUserMessage());
        response.addResult("SystemResponse", llmResponse);

        LOGGER.info("COMPLETED executeUserMessageWithMemory llmResponse {}", llmResponse);
    }
}
