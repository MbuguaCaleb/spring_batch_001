package org.codewithcaleb.spring_batch_001.batch;

import lombok.extern.slf4j.Slf4j;
import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
public class RestBookReader implements ItemReader<BookEntity> {

    private final String url;
    private final RestTemplate restTemplate;
    private int nextBook;
    private List<BookEntity> bookEntityList;

    public RestBookReader(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public BookEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
