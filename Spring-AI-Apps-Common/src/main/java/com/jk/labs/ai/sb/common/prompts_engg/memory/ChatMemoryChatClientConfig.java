package com.jk.labs.ai.sb.common.prompts_engg.memory;

import com.jk.labs.ai.sb.common.prompts_engg.advisors.JkAppTokenAuditUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatMemoryChatClientConfig {

    public static final String BEAN_ID_CHAT_MEMORY_CLIENT = "ChatMemoryChatClient";

    @Bean(BEAN_ID_CHAT_MEMORY_CLIENT)
    public ChatClient chatClient(ChatClient.Builder clientBuilder, ChatMemory chatMemory) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return clientBuilder.defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor,
                new SafeGuardAdvisor(List.of("Ford", "BMW")),
                new JkAppTokenAuditUsageAdvisor())).build();
    }
}
