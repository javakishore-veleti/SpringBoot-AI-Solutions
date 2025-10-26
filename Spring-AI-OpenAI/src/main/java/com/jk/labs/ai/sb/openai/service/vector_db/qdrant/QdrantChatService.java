package com.jk.labs.ai.sb.openai.service.vector_db.qdrant;

import com.jk.labs.ai.sb.common.dto.AppChatRequest;
import com.jk.labs.ai.sb.common.dto.AppChatResponse;

@SuppressWarnings("unused")
public interface QdrantChatService {

    void executeUserMessage(AppChatRequest request, AppChatResponse response);
}
