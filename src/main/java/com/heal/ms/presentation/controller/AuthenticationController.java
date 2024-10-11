package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.LoginUserCommand;
import com.heal.ms.application.records.LoginUserRecord;
import com.heal.ms.application.usecases.LoginUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 09:38:35
 */
@RestController
@RequestMapping("${server.path}/auth")
public class AuthenticationController {

    private final LoginUserUseCase loginUserUseCase;

    public AuthenticationController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping
    public ResponseEntity<LoginUserRecord> loginUser(@RequestBody LoginUserCommand command) {
        return ResponseEntity.ok(loginUserUseCase.execute(command));
    }
}
