package com.githubappi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GitHubBranchDTO {
    public String name;
    public Commit commit;
}


