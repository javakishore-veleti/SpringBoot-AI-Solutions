package com.jk.labs.ai.sb.openai.api;

import com.jk.labs.ai.sb.common.dto.ChatRequest;
import com.jk.labs.ai.sb.common.dto.ChatResponse;
import com.jk.labs.ai.sb.openai.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sb-ai/openai/v1", produces = "application/json")
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @PostMapping(path = "/chat/hello")
    public ResponseEntity<ChatResponse> executeUserMessage(@RequestBody ChatRequest chatRequest) {
        LOGGER.info("STARTED executeUserMessage API");

        ChatResponse chatResponse = new ChatResponse();
        chatService.executeUserMessage(chatRequest, chatResponse);

        LOGGER.info("COMPLETED executeUserMessage API");
        return ResponseEntity.ok(chatResponse);
    }

}
