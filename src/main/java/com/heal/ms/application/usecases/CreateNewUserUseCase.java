package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.CreateNewUserCommand;
import com.heal.ms.application.records.CreateNewUserRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.User;
import com.heal.ms.domain.repositories.UserRepository;
import com.heal.ms.domain.valueobjects.Password;
import com.heal.ms.domain.valueobjects.Username;
import org.springframework.stereotype.Component;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 03:07:14
*/
@Component
public class CreateNewUserUseCase implements BaseUseCase<CreateNewUserCommand, CreateNewUserRecord> {

    private final UserRepository<User, UniqueId> userRepository;

    public CreateNewUserUseCase(UserRepository<User, UniqueId> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateNewUserRecord execute(CreateNewUserCommand command) {
        User user = userRepository.save(new User(
                new Username(command.username()),
                new Password(command.password())));

        return new CreateNewUserRecord(
                user.getId().getId(),
                user.getUsername().getUsername(),
                user.getTimestamps());
    }
}
