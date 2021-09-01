package com.poc.springBatch.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.poc.springBatch.batch.model.User;
import com.poc.springBatch.batch.repository.UserRepository;

@Configuration
@EnableBatchProcessing
public class MySpringBatchConfig {
	
	//Get all rows process date is null - 18
	//resource for BS - 18 resources
	//

	
	
	
	@Bean
	public Job processDB(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<List<User>> itemreader,
           // ItemProcessor<List<User>, List<User>> itemProcessor,
            ItemWriter<List<User>> mywriter
            ) {
		
		
		 Step step = stepBuilderFactory.get("db-load")
	                .<List<User>, List<User>>chunk(2)
	                .reader(itemreader)
	              //  .processor(itemProcessor)
	                .writer(mywriter)
	                .build();
		 return jobBuilderFactory.get("db-Load")
	                .incrementer(new RunIdIncrementer())
	                .start(step)
	                .build();		
				
		
				
		
	}
            
	
	
	@Bean
	@Qualifier("itemreader")
	@StepScope
	public MySpringItemReader myreader() {
		return new MySpringItemReader();
	}
	
	@Bean
	@Qualifier("mywriter")
	public MySpringWriter mywriter() {
		return new MySpringWriter();
	}
	//@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<User> itemReader,
            ItemProcessor<User, User> itemProcessor,
            ItemWriter<User> itemWriter) {
		
		 Step step = stepBuilderFactory.get("ETL-file-load")
	                .<User, User>chunk(2)
	                .reader(itemReader)
	                .processor(itemProcessor)
	                .writer(itemWriter)
	                .build();


	        return jobBuilderFactory.get("ETL-Load")
	                .incrementer(new RunIdIncrementer())
	                .start(step)
	                .build();
	}
	
	@Bean
    public FlatFileItemReader<User> itemReader() {

        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/users.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<User> lineMapper() {

        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "name", "department", "career");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
