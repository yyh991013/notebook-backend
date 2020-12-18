package com.notebook.notebookbackend.service;

import com.notebook.notebookbackend.data.database.DO.RecordDO;
import com.notebook.notebookbackend.dto.RecordAddDTO;
import com.notebook.notebookbackend.dto.RecordEditDTO;
import com.notebook.notebookbackend.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 22454
 */
@Service
public interface RecordService {

    /**
     * 获取指定用户的所有记录
     * @param name 用户名
     * @return 所有记录列表
     * @throws BusinessException 业务异常
     */
    List<RecordDO> getAll(String name) throws BusinessException;

    /**
     * 获取指定用户指定记录
     * @param id 记录id
     * @return 记录
     * @throws BusinessException 业务异常
     */
    RecordDO get(int id) throws BusinessException;

    /**
     * 添加一条记录
     * @param recordAddData 需要添加的记录信息记录信息
     * @param username 需要添加记录的用户名
     * @throws BusinessException 业务异常
     */
    void add(RecordAddDTO recordAddData, String username) throws BusinessException;

    /**
     * 删除一条记录
     * @param id 记录id
     * @return 删除结果
     * @throws BusinessException 业务异常
     */
    int remove(int id) throws BusinessException;

    /**
     * 编辑一条记录
     * @param recordEditData 记录信息
     * @return 编辑结果
     * @throws BusinessException 业务异常
     */
    int edit(RecordEditDTO recordEditData) throws BusinessException;
}
