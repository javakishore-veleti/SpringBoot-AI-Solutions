package com.jk.labs.ai.sb.aws.bedrock.service;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;

public interface AwsBedrockChatService {

    void executeUserMessage(AppChatRequest request, AppChatResponse response);
}
