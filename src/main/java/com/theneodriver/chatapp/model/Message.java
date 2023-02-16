package com.theneodriver.chatapp.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "content")
    private String content;
    
    @NotNull
    @Column(name = "post_date")
    private Date postDate;
    
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    
    @OneToOne(mappedBy = "message")
    private MessageRecord record;
}
