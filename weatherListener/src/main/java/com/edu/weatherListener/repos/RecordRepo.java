package com.edu.weatherListener.repos;

import com.edu.weatherListener.domain.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepo extends CrudRepository<Record, Long> {
    Page<Record> findAll(Pageable pageable);

    Page<Record> findAllByDateStartingWith(String filter, Pageable pageable);
}
