package com.githubappi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Branch {
    private String name;
    private String lastCommitSha;

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", lastCommitSha='" + lastCommitSha + '\'' +
                '}';
    }
}