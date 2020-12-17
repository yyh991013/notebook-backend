package com.notebook.notebookbackend.service;

import com.notebook.notebookbackend.data.database.entity.Record;
import com.notebook.notebookbackend.data.database.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordMapper recordMapper;

    @Autowired
    public RecordService(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    public List<Record> getAllRecord(int userId) {
        return recordMapper.getAllByUserId(userId);
    }

    public Record getRecord(int recordId) {
        return recordMapper.getByRecordId(recordId);
    }

    public void addRecord(Record record) {
        recordMapper.insertRecord(record);
    }

    public boolean updateRecord(Record record) {
        Record current = recordMapper.getByRecordId(record.getRecordId());

        if (current == null) {
            return false;
        }

        recordMapper.updateRecord(record);
        return true;
    }

    public boolean removeRecord(Record record) {
        Record current = recordMapper.getByRecordId(record.getRecordId());

        if (current == null) {
            return false;
        }

        recordMapper.removeRecord(record);
        return true;
    }
}
