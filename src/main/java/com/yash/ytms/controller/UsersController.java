package com.yash.ytms.controller;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.YtmsUserDto;
import com.yash.ytms.services.IServices.IYtmsUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Project Name - ytms-api
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 25-01-2024
 */
@RestController
@RequestMapping("/users")
public class UsersController {
	
	final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private IYtmsUserService userService;

    @PreAuthorize("hasRole('ROLE_TECHNICAL_MANAGER')")
    @GetMapping("/pending")
    public ResponseEntity<List<YtmsUserDto>> getPendingUsers() {
    	LOGGER.info("Getting pending users");
        return new ResponseEntity<>(userService.getAllPendingUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICAL_MANAGER')")
    @PostMapping("/approve")
    public ResponseEntity<Boolean> approvePendingUser(@RequestParam String emailAdd) {
        Boolean status = this.userService.approvePendingUser(emailAdd);
        LOGGER.info("Approved pending user");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICAL_MANAGER')")
    @PostMapping("/decline")
    public ResponseEntity<Boolean> declinePendingUser(@RequestParam String emailAdd) {
        Boolean status = this.userService.declinePendingUser(emailAdd);
        LOGGER.info("Decline pending user");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/forgotPassword")
    public ResponseEntity<ResponseWrapperDto> forgotPassword(@RequestParam String email) {
    	LOGGER.info("Forgot password");
    	return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public Boolean resetPassword(@RequestBody Map<String, String> map) {
    	LOGGER.info("Reset password");
        return this.userService.resetPassword(map);
    }

    @PostMapping("/changePassword")
    public ResponseWrapperDto changePassword(@RequestBody Map<String, String> map) {
    	LOGGER.info("Change password");
        return this.userService.changePassword(map);
    }

    @GetMapping("/get/all-trainers")
    public ResponseEntity<List<YtmsUserDto>> getAllTrainers() {
        List<YtmsUserDto> allTrainers = this.userService.getAllTrainers();
        LOGGER.info("Getting all trainers");
        return new ResponseEntity<>(allTrainers, HttpStatus.OK);
    }
}
