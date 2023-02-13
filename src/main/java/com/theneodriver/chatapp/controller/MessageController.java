package com.theneodriver.chatapp.controller;

import com.theneodriver.chatapp.dto.MessageRecordDto;
import com.theneodriver.chatapp.dto.Response;
import com.theneodriver.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class MessageController {
    
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public  static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    @Autowired
    private MessageService service;

    // get all posts rest api
    @GetMapping("/{id}")
    public Response<MessageRecordDto> findAllMessages(
            @PathVariable("id") Long id,
            @RequestParam(value = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER,
                        required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE,
                        required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY,
                        required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION,
                        required = false) String sortDir
    ){
        return service.findAllMessagesByConversationId(
                id, pageNumber, pageSize, sortBy, sortDir
        );
    }

}
