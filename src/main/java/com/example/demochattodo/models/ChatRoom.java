package com.example.demochattodo.models;

import lombok.*;

import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter@Getter
public class ChatRoom {

    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private String chatId;

}
