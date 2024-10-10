package com.heal.ms.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 02:59:25
*/
@Getter
@NoArgsConstructor
public class Username {

    private String username;

    public Username(String username) {
        this.username = username;
    }
}
