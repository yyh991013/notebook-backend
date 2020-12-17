package com.notebook.notebookbackend.data.database.mapper;

import com.notebook.notebookbackend.data.database.entity.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("SELECT * FROM Record")
    List<Record> getAll();

    @Select("SELECT * FROM Record WHERE UserID = #{id}")
    List<Record> getAllByUserId(int id);

    @Select("SELECT * FROM Record WHERE RecordID = #{id}")
    Record getByRecordId(int id);

    @Select("SELECT SortName FROM Record WHERE UserID = #{id}")
    List<String> getAllSortByUserId(int id);

    @Insert("INSERT INTO Record (UserID, SortName, RecordTitle, RecordContent) VALUES (#{userId}, #{sortName}, #{recordTitle}, #{recordContent})")
    void insertRecord(Record record);

    @Update("UPDATE Record SET SortName = #{sortName} AND RecordTitle = #{recordTitle} AND RecordContent = #{recordContent} WHERE RecordID = #{recordId} AND UserID = #{userId}")
    void updateRecord(Record record);

    @Delete("DELETE FROM Record WHERE RecordID = #{recordId}")
    void removeRecord(Record record);
}
