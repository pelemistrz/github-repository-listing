package com.githubappi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GitHubRepo {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;

    public GitHubRepo(String name, String ownerLogin) {
        this.repositoryName = name;
        this.ownerLogin = ownerLogin;
    }

    @Override
    public String toString() {
        return "GitHubRepo{" +
                "name='" + repositoryName + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", branches=" + branches +
                '}';
    }
}