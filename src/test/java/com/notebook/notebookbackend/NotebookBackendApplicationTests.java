package com.notebook.notebookbackend;

import com.notebook.notebookbackend.dto.LoginDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotebookBackendApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() throws BusinessException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName("SBXSYZL");
        loginDTO.setPassword("456789");
        System.out.println(userService.login(loginDTO));
    }

}
