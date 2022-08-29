package com.example.demochattodo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    @Id
    private String id;
    private String senderId;
    private String senderName;
}
