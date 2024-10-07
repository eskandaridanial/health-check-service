package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.CreateNewHttpResourceCommand;
import com.heal.ms.application.commands.DeleteHttpResourceCommand;
import com.heal.ms.application.commands.UpdateHttpResourceCommand;
import com.heal.ms.application.records.CreateNewHttpResourceRecord;
import com.heal.ms.application.records.DeleteHttpResourceRecord;
import com.heal.ms.application.records.UpdateHttpResourceRecord;
import com.heal.ms.application.usecases.CreateNewHttpResourceUseCase;
import com.heal.ms.application.usecases.DeleteHttpResourceUseCase;
import com.heal.ms.application.usecases.UpdateHttpResourceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:13:27
 */
@RestController
@RequestMapping("${server.path}/http-resource")
public class HttpResourceController {

    private final CreateNewHttpResourceUseCase createNewHttpResourceUseCase;
    private final UpdateHttpResourceUseCase updateHttpResourceUseCase;
    private final DeleteHttpResourceUseCase deleteHttpResourceUseCase;

    public HttpResourceController(CreateNewHttpResourceUseCase createNewHttpResourceUseCase, UpdateHttpResourceUseCase updateHttpResourceUseCase, DeleteHttpResourceUseCase deleteHttpResourceUseCase) {
        this.createNewHttpResourceUseCase = createNewHttpResourceUseCase;
        this.updateHttpResourceUseCase = updateHttpResourceUseCase;
        this.deleteHttpResourceUseCase = deleteHttpResourceUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateNewHttpResourceRecord> createNewHttpResource(@RequestBody CreateNewHttpResourceCommand command) {
        return ResponseEntity.ok(createNewHttpResourceUseCase.execute(command));
    }

    @PutMapping
    public ResponseEntity<UpdateHttpResourceRecord> updateHttpResource(@RequestBody UpdateHttpResourceCommand command) {
        return ResponseEntity.ok(updateHttpResourceUseCase.execute(command));
    }

    @DeleteMapping
    public ResponseEntity<DeleteHttpResourceRecord> updateHttpResource(@RequestBody DeleteHttpResourceCommand command) {
        return ResponseEntity.ok(deleteHttpResourceUseCase.execute(command));
    }
}
