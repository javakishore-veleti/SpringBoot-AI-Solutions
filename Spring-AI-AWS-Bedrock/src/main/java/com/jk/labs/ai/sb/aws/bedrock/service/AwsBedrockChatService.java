package com.jk.labs.ai.sb.aws.bedrock.service;

import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;

public interface AwsBedrockChatService {

    void executeUserMessage(ChatRequest request, ChatResponse response);
}
