package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.CreateNewUserCommand;
import com.heal.ms.application.records.CreateNewUserRecord;
import com.heal.ms.application.usecases.CreateNewUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 03:10:25
 */
@RestController
@RequestMapping("${server.path}/users")
public class UserController {

    private final CreateNewUserUseCase createNewUserUseCase;

    public UserController(CreateNewUserUseCase createNewUserUseCase) {
        this.createNewUserUseCase = createNewUserUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateNewUserRecord> createNewUser(@RequestBody CreateNewUserCommand command) {
        return ResponseEntity.ok(createNewUserUseCase.execute(command));
    }
}
