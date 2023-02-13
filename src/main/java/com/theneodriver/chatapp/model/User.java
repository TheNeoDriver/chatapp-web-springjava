package com.theneodriver.chatapp.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotNull
    @Column(name = "name", unique = true)
    private String name;
    
    @NotNull
    @Column(name = "password")
    private String password;
    
    @OneToOne
    @JoinColumn(name = "image_id")
    private String imageLink;
    
    @ManyToMany(mappedBy = "user")
    private List<Conversation> conversations;
    
    @OneToOne(mappedBy = "user")
    private MessageRecord record;
}
