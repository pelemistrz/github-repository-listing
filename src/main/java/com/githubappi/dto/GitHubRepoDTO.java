package com.githubappi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GitHubRepoDTO {
    private String name;
    private boolean isFork;
    private OwnerDTO owner;

    @Override
    public String toString() {
        return "GitHubRepoDTO{" +
                "name='" + name + '\'' +
                ", isFork=" + isFork +
                ", owner=" + owner +
                '}';
    }
}
