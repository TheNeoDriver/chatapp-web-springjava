package com.theneodriver.chatapp.service;

import com.theneodriver.chatapp.dto.PageResponse;
import com.theneodriver.chatapp.dto.UserDto;
import com.theneodriver.chatapp.model.User;
import com.theneodriver.chatapp.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    
    public UserDto authorizeUser(User userLogin) {
        User user = repository
                .findByName(userLogin.getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        
        String encryptedPassword = encryptPassword(userLogin.getPassword());
        if (! user.getPassword().equals(encryptedPassword)) {
            return null;
        }
        
        UserDto dto = new UserDto(
                user.getName(),
                user.getImage().getLink(),
                user.getConversations()
        );
        return dto;
    }
    
    
    @Transactional
    public PageResponse<UserDto> findAllByName (
            String name,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);  
        Page<User> users = repository
                                .findByNameContainingIgnoreCase(name, pageable);
        
        List<UserDto> content = users.getContent()
                                        .stream()
                                        .map(user -> mapToDto(user))
                                        .collect(Collectors.toList());
        
        PageResponse<UserDto> response = new PageResponse<>(
                        content,
                        users.getNumber(),
                        users.getSize(),
                        users.getTotalElements(),
                        users.getTotalPages(),
                        users.isLast()
                );
        
        return response;
    }
    
    
    @Transactional
    public UserDto save(User user) {
        return mapToDto(repository.save(user));
    }
    
    
    @Transactional
    public UserDto update(String actualName, User userUpdated) {
        User user = repository
                .findByName(actualName)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        
        user.setName(userUpdated.getName());
        user.setPassword(encryptPassword(userUpdated.getPassword()));
        
        return mapToDto(repository.save(user));
    }
    
    
    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
    
    
    private String encryptPassword(String password) {
        return "";
    }
    
    
    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setImageLink(user.getImage().getLink());
        return dto;
    }
    
}
