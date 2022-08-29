package com.example.demochattodo.controllers;

import com.example.demochattodo.models.ChatMessage;
import com.example.demochattodo.models.ChatNotification;
import com.example.demochattodo.services.ChatMessageService;
import com.example.demochattodo.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    private final ChatRoomService chatRoomService;

    //send chatMessage
    @MessageMapping("/message")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        Optional<String> chatId =
                chatRoomService.getChatId(chatMessage.getSenderId(),
                        chatMessage.getRecipientId(), true);

        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    //count new messages
    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    //count chats
    // ???????????????????????????????
    @GetMapping()
    public Long countChats() {
        return null;
    }


    //find chat messages
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages (@PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }


    //find message
    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage (@PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}
