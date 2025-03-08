package com.githubappi.service;


import com.githubappi.dto.GitHubRepoDTO;
import com.githubappi.model.Branch;
import com.githubappi.model.GitHubRepo;
import com.githubappi.webclient.GitHubClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ApplicationScoped
public class GitHubService {

    @Inject
    @RestClient
    GitHubClient gitHubClient;

    public Uni<List<GitHubRepo>> getRepositories(String user) {

        return Uni.createFrom().item(() -> {
            List<GitHubRepoDTO> repos = gitHubClient.getUserRepositories(user);
            log.info("Got repositories: {}", repos);
            if (repos.isEmpty()) {
                throw new IllegalArgumentException("User not found");
            }
            return repos.stream()
                    .filter(repo -> !repo.isFork())
                    .map(repo -> {

                        GitHubRepo gitHubRepo = new GitHubRepo(repo.getName(), repo.getOwner().getLogin());

                        List<Branch> branches = gitHubClient.getBranches(repo.getOwner().getLogin(), repo.getName())
                                .stream()
                                .map(branch -> {
                                    Branch b = new Branch(branch.getName(), branch.getCommit().getSha());
                                    return b;
                                })
                                .collect(Collectors.toList());
                        gitHubRepo.setBranches(branches);

                        log.info(gitHubRepo.toString());
                        return gitHubRepo;
                    })
                    .collect(Collectors.toList());
        });
    }
}