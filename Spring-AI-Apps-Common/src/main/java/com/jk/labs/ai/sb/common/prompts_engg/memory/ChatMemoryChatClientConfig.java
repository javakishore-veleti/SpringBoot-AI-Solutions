package com.jk.labs.ai.sb.common.prompts_engg.memory;

import com.jk.labs.ai.sb.common.prompts_engg.advisors.JkAppTokenAuditUsageAdvisor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("vector-db-qdrant")
@Configuration
public class ChatMemoryChatClientConfig {

    public static final String BEAN_ID_CHAT_MEMORY_CLIENT = "ChatMemoryChatClient";
    public static final String BEAN_ID_CHAT_MEMORY_WITH_MAX_MESSAGES = "ChatMemoryWithMaxMessages";
    public static final String BEAN_ID_CHAT_MEMORY_LIMIT_CLIENT = "ChatMemorLimityChatClient";
    public static final String BEAN_ID_CHAT_MEMORY_LIMIT_CLIENT_RAG_ADVISOR = "ChatMemorLimityChatClientRagAvsior";

    @Bean(BEAN_ID_CHAT_MEMORY_WITH_MAX_MESSAGES)
    public ChatMemory chatMemoryWithMaxMessages(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(20).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean(BEAN_ID_CHAT_MEMORY_CLIENT)
    public ChatClient chatClient(ChatClient.Builder clientBuilder, ChatMemory chatMemory, List<Advisor> advisors) {
        if(ObjectUtils.isEmpty(advisors)) {
            advisors = new ArrayList<>();
        }

        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        List<Advisor> advisorsList = List.of(loggerAdvisor, memoryAdvisor,
                new SafeGuardAdvisor(List.of("Ford", "BMW")),
                new JkAppTokenAuditUsageAdvisor());

        advisors.add(loggerAdvisor);
        advisors.addAll(advisorsList);

        return clientBuilder.defaultAdvisors(advisors).build();
    }

    @Bean(BEAN_ID_CHAT_MEMORY_LIMIT_CLIENT)
    public ChatClient chatClientWithMemoryLimit(ChatClient.Builder clientBuilder, @Qualifier(BEAN_ID_CHAT_MEMORY_WITH_MAX_MESSAGES) ChatMemory chatMemory) {
        return chatClient(clientBuilder, chatMemory,null);
    }

    @Bean(BEAN_ID_CHAT_MEMORY_LIMIT_CLIENT_RAG_ADVISOR)
    public ChatClient chatClientWithMemoryLimitAndRagAdvisor(ChatClient.Builder clientBuilder, @Qualifier(BEAN_ID_CHAT_MEMORY_WITH_MAX_MESSAGES) ChatMemory chatMemory, RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {
        return chatClient(clientBuilder, chatMemory, List.of(retrievalAugmentationAdvisor));
    }
}
