package com.example.demochattodo.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
public class ChatMessage {

   @Id
   private String id;
   private String content;
   private Date timestamp;
   private MessageStatus status;
   private String chatId;
   private String senderId;
   private String recipientId;
   private String senderName;
   private String recipientName;

}
