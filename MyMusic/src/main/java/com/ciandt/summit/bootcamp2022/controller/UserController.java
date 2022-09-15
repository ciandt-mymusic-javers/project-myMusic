package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Find user by Id", description = "Find user by Id")
    @GetMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Returns 'Find User successfully '")
    @ApiResponse(responseCode = "400", description = "Returns 'User with id {} not found'")
    public ResponseEntity<User> findUser(@PathVariable String userId){
        User user = userService.findUser(userId);

        log.info("Endpoint to find user by Id initialized.");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
