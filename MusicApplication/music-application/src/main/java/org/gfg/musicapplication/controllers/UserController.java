package org.gfg.musicapplication.controllers;

import org.gfg.musicapplication.requests.UserSignupRequest;
import org.gfg.musicapplication.response.GenericMessages;
import org.gfg.musicapplication.response.GenericResponse;
import org.gfg.musicapplication.response.SignUpResponse;
import org.gfg.musicapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup/{type}")
    public GenericResponse<Object> userSignUp(@PathVariable String type,
            @Valid @RequestBody UserSignupRequest userSignupRequest){

        SignUpResponse signUpResponse = userService.userSignUp(userSignupRequest, type);

        if(signUpResponse.getMessage().equalsIgnoreCase(GenericMessages.SIGNUPSUCCESS.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.SIGNUPSUCCESS.getMessage()).
                    statusCode(0).
                    data(signUpResponse).build();
        }

        else if (signUpResponse.getMessage().equalsIgnoreCase(GenericMessages.ENTRYALREADYPRESENT.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).
                    statusCode(1).
                    data(signUpResponse).build();

        }
        return GenericResponse.builder().
                code(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                message(GenericMessages.SIGNUPFAILURE.getMessage()).
                statusCode(1).
                data(null).
                build();
    }


}
