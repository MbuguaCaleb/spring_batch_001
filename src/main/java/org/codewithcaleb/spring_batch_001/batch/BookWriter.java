package org.codewithcaleb.spring_batch_001.batch;

import lombok.extern.slf4j.Slf4j;
import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.codewithcaleb.spring_batch_001.repository.BookRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class BookWriter implements ItemWriter<BookEntity> {

    @Autowired
    private BookRepository bookRepository;

    /***
      Remember processing is being done in chunks Spring Batch is much faster & is more resilient
     **/
    @Override
    public void write(Chunk<? extends BookEntity> chunk) throws Exception {
        log.info("writing {}",chunk.getItems().size());
        bookRepository.saveAll(chunk.getItems());
    }
}
