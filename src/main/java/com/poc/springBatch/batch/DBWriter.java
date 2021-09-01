package com.poc.springBatch.batch;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.springBatch.batch.model.User;
import com.poc.springBatch.batch.repository.UserRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {

    private UserRepository userRepository;

    @Autowired
    public DBWriter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends User> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        userRepository.saveAll(users);
    }
}
