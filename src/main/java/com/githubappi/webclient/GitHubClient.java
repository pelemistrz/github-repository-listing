package com.githubappi.webclient;

import com.githubappi.dto.GitHubBranchDTO;
import com.githubappi.dto.GitHubRepoDTO;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.List;


@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubClient {

    @GET
    @Path("/users/{user}/repos")
List<GitHubRepoDTO> getUserRepositories(@PathParam("user") String user);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    List<GitHubBranchDTO> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}