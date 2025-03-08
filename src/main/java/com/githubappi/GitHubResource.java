package com.githubappi;

import com.githubappi.errors.Error404;
import com.githubappi.service.GitHubService;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Blocking
@Path("/api/github/repos")
@Produces(MediaType.APPLICATION_JSON)
public class GitHubResource {

    @Inject
    GitHubService gitHubService;

    @GET
    @Path("/{user}")
    public Uni<Response> getRepositories(@PathParam("user") String user) {
        return gitHubService.getRepositories(user)
                .onItem().transform(repos -> Response.ok(repos).build()) // Happy path
                .onFailure(IllegalArgumentException.class).recoverWithItem(() ->
                        Response.status(Response.Status.NOT_FOUND)
                                .entity(new Error404(404, "User not found"))
                                .build());
    }
}