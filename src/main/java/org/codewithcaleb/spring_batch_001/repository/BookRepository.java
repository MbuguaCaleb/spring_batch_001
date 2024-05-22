package org.codewithcaleb.spring_batch_001.repository;

import org.codewithcaleb.spring_batch_001.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
