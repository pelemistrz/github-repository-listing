package com.githubappi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor

public class GitHubRepoDTO {
    public String name;
    public boolean isFork;
    public Owner owner;

    @Override
    public String toString() {
        return "GitHubRepoDTO{" +
                "name='" + name + '\'' +
                ", isFork=" + isFork +
                ", owner=" + owner +
                '}';
    }
}
