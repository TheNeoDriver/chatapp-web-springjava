package com.theneodriver.chatapp.dto;

import com.theneodriver.chatapp.model.Conversation;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private String imageLink;
    private Set<Conversation> conversations;
}
