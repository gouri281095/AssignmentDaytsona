package com.userms.userms.api;


import com.userms.userms.dto.UserManagementDAO;
import com.userms.userms.service.UserManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

@RestController("/management")
public class UserManangementController {

    @Autowired
    private UserManagementService userManagementService;

    private Logger logger = LogManager.getLogger(UserManagementService.class);

    @RolesAllowed({"ADMIN"})
    @PostMapping("/create")
    public UserManagementDAO createUser(@RequestBody UserManagementDAO userManagementDAO,HttpSession session)
    {
        try {
            String user = (String) session.getAttribute("username");
            FileWriter fileWriter = new FileWriter("logManager.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("User "+user+"performed create user on "+new Date());
            bufferedWriter.close();

        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return userManagementService.createUser(userManagementDAO);
    }

    @RolesAllowed({"ADMIN,USER"})
    @PutMapping("/update")
    public UserManagementDAO updateUser(@RequestBody UserManagementDAO userManagementDAO, HttpSession session) {
        try {
            String user = (String) session.getAttribute("username");
            FileWriter fileWriter = new FileWriter("logManager.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("User "+user+"performed update user on "+new Date());
            bufferedWriter.close();

        }catch (Exception e)
        {
          logger.error(e.getMessage());
        }
        return userManagementService.updateUser(userManagementDAO);
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/findAll")
    public List<UserManagementDAO>  findAll(HttpSession session)
    {
        try {
            String user = (String) session.getAttribute("username");
            FileWriter fileWriter = new FileWriter("logManager.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("User "+user+"performed find user on "+new Date());
            bufferedWriter.close();
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return userManagementService.findAllUsers();
    }

    @RolesAllowed({"ADMIN,USER"})
    @PutMapping("/changePassword")
    public UserManagementDAO changePassword(@RequestBody UserManagementDAO userManagementDAO,HttpSession session)
    {

        try
        {
            String user = (String) session.getAttribute("username");
            FileWriter fileWriter = new FileWriter("logManager.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("User "+user+"performed find user on "+new Date());
            bufferedWriter.close();
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return userManagementService.changePassword(userManagementDAO);
    }
}
