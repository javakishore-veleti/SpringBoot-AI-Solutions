package com.jk.labs.ai.sb.common.prompts_engg.advisors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.util.ObjectUtils;

// JK means javakishore
public class JkAppTokenAuditUsageAdvisor implements CallAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JkAppTokenAuditUsageAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = response.chatResponse();

        if(!ObjectUtils.isEmpty(chatResponse.getMetadata())) {
            Usage usage = chatResponse.getMetadata().getUsage();
            if(!ObjectUtils.isEmpty(usage)) {
                LOGGER.info("Token Usage Details {} ", usage);
            }
        }
        return response;
    }

    @Override
    public String getName() {
        return "JkAppTokenAuditUsageAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
