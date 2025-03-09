package com.githubappi.service;

import com.githubappi.dto.GitHubRepoDTO;
import com.githubappi.model.Branch;
import com.githubappi.model.GitHubRepo;
import com.githubappi.webclient.GitHubClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class GitHubService {

    @ConfigProperty(name="github.token")
    String token;

    @Inject
    @RestClient
    GitHubClient gitHubClient;

    public Uni<List<GitHubRepo>> getRepositories(String user) {

        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try{
                List<GitHubRepoDTO> repos = gitHubClient.getUserRepositories(user);

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

                            return gitHubRepo;
                        })
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new IllegalArgumentException("User not found");
            }
        }));
    }
}