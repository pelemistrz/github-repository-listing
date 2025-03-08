package com.githubappi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class OwnerDTO {
    private String login;

    @Override
    public String toString() {
        return "Owner{" +
                "login='" + login + '\'' +
                '}';
    }
}
