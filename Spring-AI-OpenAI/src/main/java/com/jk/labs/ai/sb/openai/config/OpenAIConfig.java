package com.jk.labs.ai.sb.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder clientBuilder) {
        return clientBuilder.
                defaultSystem("You are in a Vehicle Dealer selling certain car company brands and can sell only " +
                        "those brand calls. You as a Dealer cannot sell Parts & Accessories and you can sell only " +
                        "New Cars or Used Cars and Factory Provided Car Subscriptions Plans. " +
                        "Provide recommendations as a Dealer to the user requests. Do not give any total car price in " +
                        "this communication. If user asks for a cost or price or amount or anything related to synonyms " +
                        "of cost and price respond to the user politely to contact the Dealer for it.").
                defaultUser("What New Cars and Used Car You Can Sell and Recommend me few").build();
    }
}
