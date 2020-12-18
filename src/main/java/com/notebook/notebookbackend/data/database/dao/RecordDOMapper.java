package com.notebook.notebookbackend.data.database.dao;

import com.notebook.notebookbackend.data.database.DO.RecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordDOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(RecordDO record);

    int insertSelective(RecordDO record);

    RecordDO selectByPrimaryKey(Integer recordId);

    List<RecordDO> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(RecordDO record);

    int updateByPrimaryKeyWithBLOBs(RecordDO record);

    int updateByPrimaryKey(RecordDO record);
}