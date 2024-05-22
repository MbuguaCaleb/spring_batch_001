package org.codewithcaleb.spring_batch_001.config;

import org.codewithcaleb.spring_batch_001.batch.BookAuthorProcessor;
import org.codewithcaleb.spring_batch_001.batch.BookTitleProcessor;
import org.codewithcaleb.spring_batch_001.batch.BookWriter;
import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public Job bookReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("bookReadJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository,transactionManager)).build();
    }


    //Defining a Chunked Step,
    //When we get to 10 Items after reading and processing, that is when we will invoke our writer
    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
     return new StepBuilder("bookReaderStep",jobRepository).<BookEntity,BookEntity>
        chunk(10,transactionManager)
             .reader(reader())
             .processor(processor())
             .writer(writer())
             .build();
    }


    @Bean
    @StepScope
    public ItemWriter<BookEntity> writer(){
     return new BookWriter();
    }

    @Bean
    @StepScope
    public ItemProcessor<BookEntity,BookEntity> processor(){
        //Combining Two ItemProcessors is done as Below
        CompositeItemProcessor<BookEntity,BookEntity> processor = new CompositeItemProcessor<>();
        processor.setDelegates(List.of(new BookTitleProcessor(),new BookAuthorProcessor()));
        return processor;
    }

    /**Configuring a Reader
    Can also be a custom class.New Flat fileItem reader that returns a book entity
     My reader is going to read CSV and based on the three delimiters, group each item into title,author,year_of_publishing
     (The reader will pass into the processor an entity item representing a Book.)
     **/
    @Bean
    @StepScope
    public FlatFileItemReader<BookEntity> reader(){
        return new FlatFileItemReaderBuilder<BookEntity>()
                .name("bookReader")
                .resource(new ClassPathResource("book_data.csv"))
                .delimited()
                .names(new String[]{"title","author","year_of_publishing"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(BookEntity.class);
                }}).build();

    }
}
