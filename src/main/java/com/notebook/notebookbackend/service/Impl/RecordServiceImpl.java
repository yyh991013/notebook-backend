package com.notebook.notebookbackend.service.Impl;

import com.notebook.notebookbackend.BO.UserBO;
import com.notebook.notebookbackend.data.database.DO.RecordDO;
import com.notebook.notebookbackend.data.database.dao.RecordDOMapper;
import com.notebook.notebookbackend.data.database.dao.UserDOMapper;
import com.notebook.notebookbackend.dto.RecordAddDTO;
import com.notebook.notebookbackend.dto.RecordEditDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.service.RecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 22454
 */
@Service
public class RecordServiceImpl implements RecordService {
    private final RecordDOMapper recordDOMapper;
    private final UserDOMapper userDOMapper;

    public RecordServiceImpl(RecordDOMapper recordDOMapper, UserDOMapper userDOMapper) {
        this.recordDOMapper = recordDOMapper;
        this.userDOMapper = userDOMapper;
    }

    @Override
    public List<RecordDO> getAll(String name) throws BusinessException {
        try {
            UserBO user = userDOMapper.selectByUserName(name);

            if (user != null) {
                int id = user.getUserId();
                return recordDOMapper.selectByUserId(id);
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.USERNAME_INVALID);
        }
    }

    @Override
    public RecordDO get(int id) throws BusinessException {
        try {
            RecordDO record = recordDOMapper.selectByPrimaryKey(id);

            if (record != null) {
                return record;
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.INVALID_RECORD_ID);
        }
    }

    @Override
    public void add(RecordAddDTO recordAddData, String username) throws BusinessException {
        try {
            UserBO user = userDOMapper.selectByUserName(username);

            if (user != null) {
                int id = user.getUserId();
                RecordDO record = new RecordDO();
                record.setUserId(id);
                record.setRecordTitle(record.getRecordTitle());
                record.setSortName(record.getSortName());
                record.setRecordContent(record.getRecordContent());

                int result = recordDOMapper.insertSelective(record);
                if (result == 0) {
                    throw new BusinessException(EmBusinessErr.FAILED_RECORD_INSERTION);
                }
                return;
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            throw new BusinessException(EmBusinessErr.USERNAME_INVALID);
        }
    }

    @Override
    public int remove(int id) throws BusinessException {
        try {
            int result = recordDOMapper.deleteByPrimaryKey(id);

            if (result != 0) {
                return result;
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.INVALID_RECORD_ID);
        }
    }

    @Override
    public int edit(RecordEditDTO recordEditData) throws BusinessException {
        try {
            RecordDO record = recordDOMapper.selectByPrimaryKey(recordEditData.getId());

            if (record != null) {
                int result = recordDOMapper.updateByPrimaryKeySelective(record);
                if (result != 0) {
                    return result;
                }
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.INVALID_RECORD_ID);
        }
    }
}
