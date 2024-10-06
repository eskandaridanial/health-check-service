package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.CreateNewTcpResourceCommand;
import com.heal.ms.application.records.CreateNewTcpResourceRecord;
import com.heal.ms.application.usecases.CreateNewTcpResourceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:13:27
 */
@RestController
@RequestMapping("${server.path}/tcp-resource")
public class TcpResourceController {

    private final CreateNewTcpResourceUseCase createNewTcpResourceUseCase;

    public TcpResourceController(CreateNewTcpResourceUseCase createNewTcpResourceUseCase) {
        this.createNewTcpResourceUseCase = createNewTcpResourceUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateNewTcpResourceRecord> createNewTcpResource(@RequestBody CreateNewTcpResourceCommand command) {
        return ResponseEntity.ok(createNewTcpResourceUseCase.execute(command));
    }
}
