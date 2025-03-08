package com.githubappi.service;


import com.githubappi.dto.GitHubRepoDTO;
import com.githubappi.model.Branch;
import com.githubappi.model.Repository;
import com.githubappi.webclient.GitHubRepository;
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
    GitHubRepository gitHubRepository;

    public Uni<List<Repository>> getRepositories(String user) {

        return Uni.createFrom().item(() -> {
            // Pobieranie repozytoriów
            List<GitHubRepoDTO> repos = gitHubRepository.getUserRepositories(user);

            log.info("Got repositories: {}", repos);

            if (repos.isEmpty()) {
                throw new IllegalArgumentException("User not found");
            }


            return repos.stream()
                    .filter(repo -> !repo.isFork) // Filtrujemy repozytoria, które są forkami
                    .map(repo -> {
                        // Mapowanie do własnego modelu Repository
                        Repository repository = new Repository();
                        repository.name = repo.name;
                        repository.ownerLogin = repo.getOwner().getLogin();
                        // Pobieranie gałęzi i ostatniego commita
                        repository.branches = gitHubRepository.getBranches(repo.getOwner().getLogin(), repo.name)
                                .stream()
                                .map(branch -> {
                                    Branch b = new Branch();
                                    b.name = branch.name;
                                    b.lastCommitSha = branch.getCommit().getSha();
                                    return b;
                                })
                                .collect(Collectors.toList());
                        return repository;
                    })
                    .collect(Collectors.toList());
        });
    }
}