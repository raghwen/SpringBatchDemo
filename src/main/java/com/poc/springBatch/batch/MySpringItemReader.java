package com.poc.springBatch.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.springBatch.batch.model.User;
import com.poc.springBatch.batch.repository.UserRepository;

public class MySpringItemReader implements ItemReader<List<User>>, StepExecutionListener{
	
	@Autowired
	private UserRepository repo;
	
	List<User> userList = null;
	Map<String, List<User>> userBydept=null;
	
	@Override
	public List<User> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		
		if(userList == null) {
			readDB();
			userBydept = userList.stream().collect(Collectors.groupingBy(User::getDepartment)); // 2 group
			
		}
		
		if(userList != null) {
			
			if(userBydept != null && !userBydept.isEmpty()  ) {
				return userBydept.remove(userBydept.keySet().stream().findFirst().get());
			}
			
		}
		
		return null;
		
		
		
		
	}
	
	protected void  readDB(){
		userList =repo.findAll();
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		userList = null;
		userBydept=null;
		return null;
	}
	
	
	

}
