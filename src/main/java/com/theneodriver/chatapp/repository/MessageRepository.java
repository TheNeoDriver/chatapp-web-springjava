package com.theneodriver.chatapp.repository;

import com.theneodriver.chatapp.model.MessageRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageRecord, Long> {
    Page<MessageRecord> findAllByConversation_Id(Long id, Pageable pageable);
}
