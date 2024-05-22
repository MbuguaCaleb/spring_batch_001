package org.codewithcaleb.spring_batch_001.batch;

import lombok.extern.slf4j.Slf4j;
import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BookAuthorProcessor implements ItemProcessor<BookEntity,BookEntity> {
    @Override
    public BookEntity process(BookEntity item) throws Exception {
        log.info("Process author:{}",item);
        item.setAuthor("By" + item.getAuthor());
        return item;
    }
}
