package com.jk.labs.ai.sb.ollama.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaChatConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OllamaChatConfig.class);

    public OllamaChatConfig() {
        LOGGER.info("Ollama Chat Config created");
    }

    @Bean
    public ChatClient ollamaChatCLient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }
}
