package com.githubappi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GitHubBranchDTO {
    private String name;
    private CommitDTO commit;
}


