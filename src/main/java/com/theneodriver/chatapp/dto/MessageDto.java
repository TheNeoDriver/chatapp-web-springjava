package com.theneodriver.chatapp.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private long conversationId;
    private String userName;
    private String messageContent;
    private Date messagePostDate;
}
