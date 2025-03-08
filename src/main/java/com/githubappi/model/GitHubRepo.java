package com.githubappi.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GitHubRepo {
    private String name;
    private String ownerLogin;
    private List<Branch> branches;

    public GitHubRepo(String name, String ownerLogin) {
        this.name = name;
        this.ownerLogin = ownerLogin;
    }

    @Override
    public String toString() {
        return "GitHubRepo{" +
                "name='" + name + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", branches=" + branches +
                '}';
    }
}