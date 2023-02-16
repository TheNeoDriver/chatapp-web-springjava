package com.theneodriver.chatapp.service;

import com.theneodriver.chatapp.dto.MessageDto;
import com.theneodriver.chatapp.dto.PageResponse;
import com.theneodriver.chatapp.model.MessageRecord;
import com.theneodriver.chatapp.repository.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository repository;
    
    
    @Transactional
    public PageResponse<MessageDto> findAllMessagesByConversationId(
            long conversationId,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<MessageRecord> records
                = repository.findAllByConversation_Id(conversationId, pageable);
        
        List<MessageDto> content = records.getContent()
                                        .stream()
                                        .map(record -> mapToDto(record))
                                        .collect(Collectors.toList());
        
        PageResponse<MessageDto> response = new PageResponse<>(
                        content,
                        records.getNumber(),
                        records.getSize(),
                        records.getTotalElements(),
                        records.getTotalPages(),
                        records.isLast()
                );
        
        return response;
    }
    
    
    private MessageDto mapToDto(MessageRecord model) {
        MessageDto dto = new MessageDto(
            model.getConversation().getId(),
            model.getSender().getName(),
            model.getMessage().getContent(),
            model.getMessage().getPostDate()
        );
        
        return dto;
    }
    
}
