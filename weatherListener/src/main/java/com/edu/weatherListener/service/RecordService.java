package com.edu.weatherListener.service;

import com.edu.weatherListener.domain.Record;
import com.edu.weatherListener.repos.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    private RecordRepo recordRepo;

    @Autowired
    public void setRecordRepo(RecordRepo recordRepo) {
        this.recordRepo = recordRepo;
    }

    public Page<Record> findAll(Pageable pageable){
        return recordRepo.findAll(pageable);
    }

    public void save(Record record){
        recordRepo.save(record);
    }

    public void deleteById(Long id, Long userId){
        Record recordFromDB = recordRepo.findById(id).orElse(null);
        if(recordFromDB != null && userId.equals(recordFromDB.getAuthor().getId())){
            recordRepo.deleteById(id);
        }
    }

    public Page<Record> findAllByDateStartingWith(String filter, Pageable pageable) {
        Page<Record> page = recordRepo.findAll(pageable);

        if (filter != null && !filter.isEmpty()) {
            page = recordRepo.findAllByDateStartingWith(filter, pageable);
        }

        return page;
    }
}