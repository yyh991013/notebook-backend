package com.notebook.notebookbackend.service.Impl;

import com.notebook.notebookbackend.BO.UserBO;
import com.notebook.notebookbackend.data.database.dao.UserDOMapper;
import com.notebook.notebookbackend.dto.LoginDTO;
import com.notebook.notebookbackend.dto.RegisterDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.service.UserService;
import com.notebook.notebookbackend.utils.Md5Util;
import com.notebook.notebookbackend.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDOMapper userDOMapper;

    public UserServiceImpl(UserDOMapper userDoMapper) {
        this.userDOMapper = userDoMapper;
    }

    @Override
    public Map<String, String> login(LoginDTO loginDTO) throws BusinessException {
        try {
            UserBO userBO = userDOMapper.selectByUserName(loginDTO.getUserName());
            if (userBO != null && userBO.getUserPassword().equals(Md5Util.getMd5(loginDTO.getPassword()))) {
                String token = TokenUtil.createJwt(userBO.getUserName());
                Map<String, String> map = new HashMap<>(2);
                map.put("userName", userBO.getUserName());
                map.put("token", token);
                return map;
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.LOGIN_ERROR);
        }
    }

    @Override
    public String registered(RegisterDTO registerDTO) throws BusinessException {
        try {
            String userName = registerDTO.getUserName();
            UserBO userBO = userDOMapper.selectByUserName(userName);
            if (userBO != null) {
                throw new BusinessException(EmBusinessErr.USERNAME_OCCUPATION);
            }
            int tag = userDOMapper.createUser(registerDTO.getUserName(), registerDTO.getEmail(), Md5Util.getMd5(registerDTO.getPassword()), registerDTO.getPhone());
            if (tag != 0) {
                System.out.println(tag);
                return registerDTO.getUserName();
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            throw new BusinessException(EmBusinessErr.REGISTERED_ERROR);
        }
    }
}
