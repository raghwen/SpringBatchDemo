package com.poc.springBatch.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.poc.springBatch.batch.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public Processor() {
        DEPT_NAMES.put("001", "Technology");
        DEPT_NAMES.put("002", "Operations");
        DEPT_NAMES.put("003", "Accounts");
    }

    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDepartment();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDepartment(dept);
       
        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return user;
    }
}
