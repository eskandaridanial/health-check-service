package com.heal.ms.presentation.controller;

import com.heal.ms.application.commands.CreateNewHttpResourceCommand;
import com.heal.ms.application.records.CreateNewHttpResourceRecord;
import com.heal.ms.application.usecases.CreateNewHttpResourceUseCase;
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

    public HttpResourceController(CreateNewHttpResourceUseCase createNewHttpResourceUseCase) {
        this.createNewHttpResourceUseCase = createNewHttpResourceUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateNewHttpResourceRecord> createNewHttpResource(@RequestBody CreateNewHttpResourceCommand command) {
        return ResponseEntity.ok(createNewHttpResourceUseCase.execute(command));
    }
}
