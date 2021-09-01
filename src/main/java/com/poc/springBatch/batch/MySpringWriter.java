package com.poc.springBatch.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.poc.springBatch.batch.model.User;


public class MySpringWriter implements ItemWriter<List<User>>{

	@Override
	public void write(List<? extends List<User>> items) throws Exception {
		System.out.println("MySpringProcessor***" + items);
		
	}

}
