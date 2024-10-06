package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.CreateNewTcpResourceCommand;
import com.heal.ms.application.commands.DeleteTcpResourceCommand;
import com.heal.ms.application.commands.UpdateTcpResourceCommand;
import com.heal.ms.application.records.CreateNewTcpResourceRecord;
import com.heal.ms.application.records.DeleteTcpResourceRecord;
import com.heal.ms.application.records.UpdateTcpResourceRecord;
import com.heal.ms.application.usecases.CreateNewTcpResourceUseCase;
import com.heal.ms.application.usecases.DeleteTcpResourceUseCase;
import com.heal.ms.application.usecases.UpdateTcpResourceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:13:27
 */
@RestController
@RequestMapping("${server.path}/tcp-resource")
public class TcpResourceController {

    private final CreateNewTcpResourceUseCase createNewTcpResourceUseCase;
    private final UpdateTcpResourceUseCase updateTcpResourceUseCase;
    private final DeleteTcpResourceUseCase deleteTcpResourceUseCase;

    public TcpResourceController(CreateNewTcpResourceUseCase createNewTcpResourceUseCase, UpdateTcpResourceUseCase updateTcpResourceUseCase, DeleteTcpResourceUseCase deleteTcpResourceUseCase) {
        this.createNewTcpResourceUseCase = createNewTcpResourceUseCase;
        this.updateTcpResourceUseCase = updateTcpResourceUseCase;
        this.deleteTcpResourceUseCase = deleteTcpResourceUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateNewTcpResourceRecord> createNewTcpResource(@RequestBody CreateNewTcpResourceCommand command) {
        return ResponseEntity.ok(createNewTcpResourceUseCase.execute(command));
    }

    @PutMapping
    public ResponseEntity<UpdateTcpResourceRecord> deleteTcpResource(@RequestBody UpdateTcpResourceCommand command) {
        return ResponseEntity.ok(updateTcpResourceUseCase.execute(command));
    }

    @DeleteMapping
    public ResponseEntity<DeleteTcpResourceRecord> deleteTcpResource(@RequestBody DeleteTcpResourceCommand command) {
        return ResponseEntity.ok(deleteTcpResourceUseCase.execute(command));
    }
}
