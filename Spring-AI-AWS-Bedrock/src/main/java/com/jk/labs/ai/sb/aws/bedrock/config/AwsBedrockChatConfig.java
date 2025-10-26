package com.jk.labs.ai.sb.aws.bedrock.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsBedrockChatConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsBedrockChatConfig.class);

    public AwsBedrockChatConfig() {
        LOGGER.info("Ollama Chat Config created");
    }

    @Bean
    public ChatClient ollamaChatCLient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }
}
