package com.userms.userms.service;

import com.userms.userms.dto.UserManagementDAO;
import com.userms.userms.entity.UserManagement;
import com.userms.userms.exception.PasswordException;
import com.userms.userms.repository.UserManagementRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserManagementService {

    @Autowired
    private UserManagementRepo userManagementRepo;

    private Logger logger = LogManager.getLogger(UserManagementService.class);

    public UserManagementDAO createUser(UserManagementDAO userManagementDAO) {
        try {
            UserManagement userManagement = new UserManagement();
            userManagement.setDateOfBirth(userManagementDAO.getDateOfBirth());
            userManagement.setEmail(userManagementDAO.getEmail());
            userManagement.setFirstName(userManagementDAO.getFirstName());
            userManagement.setLastName(userManagementDAO.getLastName());
            userManagement.setUsername(userManagementDAO.getUsername());
            userManagement.setPassword(userManagementDAO.getPassword());
            isPasswordValid(userManagement.getPassword());

            userManagement = userManagementRepo.save(userManagement);

            userManagementDAO.setId(userManagement.getId());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return userManagementDAO;
    }

    public UserManagementDAO updateUser(UserManagementDAO userManagementDAO) {
        try {
            UserManagement userManagement = new UserManagement();
            userManagement.setDateOfBirth(userManagementDAO.getDateOfBirth());
            userManagement.setEmail(userManagementDAO.getEmail());
            userManagement.setFirstName(userManagementDAO.getFirstName());
            userManagement.setLastName(userManagementDAO.getLastName());
            userManagement.setUsername(userManagementDAO.getUsername());
            userManagement.setPassword(userManagementDAO.getPassword());
            userManagementDAO.setId(userManagement.getId());
            isPasswordValid(userManagement.getPassword());

            userManagement = userManagementRepo.save(userManagement);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return userManagementDAO;
    }

    public UserManagementDAO changePassword(UserManagementDAO userManagementDAO) {
        try {
            Optional<UserManagement> userManagement = userManagementRepo.findById(userManagementDAO.getId());

            UserManagement userManagement1 = userManagement.get();
            userManagement1.setPassword(userManagementDAO.getPassword());
             isPasswordValid(userManagement1.getPassword());
            userManagement1 = userManagementRepo.save(userManagement1);
            userManagementDAO.setLastName(userManagement1.getLastName());
            userManagementDAO.setDateOfBirth(userManagement1.getDateOfBirth());
            userManagementDAO.setFirstName(userManagement1.getFirstName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return userManagementDAO;
    }


    public List<UserManagementDAO> findAllUsers()
    {
        List<UserManagement> userManagements = userManagementRepo.findAll();

        List<UserManagementDAO> userManagementDAOS = userManagements.stream().map( e ->
        {
            UserManagementDAO userManagementDAO = new UserManagementDAO();
            userManagementDAO.setFirstName(e.getFirstName());
            userManagementDAO.setLastName(e.getLastName());
            userManagementDAO.setId(e.getId());
            userManagementDAO.setDateOfBirth(e.getDateOfBirth());
            userManagementDAO.setUsername(e.getUsername());
            userManagementDAO.setPassword(e.getPassword());
            return userManagementDAO;
        }).collect(Collectors.toList());

        return userManagementDAOS;
    }

    private void isPasswordValid(String password) throws PasswordException {
        if (password.length() < 8) {
            throw new PasswordException("Password length should be altealst 8 characters");
        }
        int numeric = 0;
        int specialChar = 0;
        for(int i=0;i<password.length();i++)
        {
            if(Character.isAlphabetic(password.charAt(i)))
            {
                numeric++;
            }
            if(Character.isSpaceChar(password.charAt(i)))
            {
                specialChar++;
            }
        }
        if(numeric == 0)
        {
            throw new PasswordException("Password  should have altealst One numeric value");
        }

        if(specialChar == 0)
        {
            throw new PasswordException("Password  should have altealst One special character value like !@#$%");
        }
    }


}
