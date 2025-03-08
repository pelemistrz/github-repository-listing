# GitHub Repository API Service

This project provides an API service to list GitHub repositories of a user

## Overview

The main functionality of the application is to list all repositories of a given GitHub username which are not forked. The API provides:

- Repository Name
- Owner Login
- For each branch: its name and the last commit SHA

It handles specific error 404 when GitHub user doesn't exist

## Table of Contents

- [Getting Started](#getting-started)
- [Usage](#usage)
- [Components](#components)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [License](#license)

## Getting Started

### Prerequisites

- Java 21
- Quarkus 3
- Maven (for building and running)

### Usage
To fetch repositories for a specific GitHub user:
```bash
GET /api/v1/repos/{username}
Accept: application/json
```

Replace `{username}` with the desired GitHub username.

## **Components**

### Resources

- `GitHubResource`: The main resource handling incoming HTTP requests.

### Services

- `GitHubService`: Responsible for fetching data from the GitHub API.


### Models

- `Branch`: Represents a branch in a GitHub repository.
- `GitHubRepo`: Represents a GitHubRepository.
### DTO
- `CommitDTO`: Represents a commit in a GitHub repository.
- `OwnerDTO`: Represents the owner of a GitHub repository.
- `GitHubRepoDTO`: Represents detailed information about a repository.
- `GitHubBranchDTO`: Represents a GitHub branch.

### Exceptions

- `Error404`: Thrown when a specified GitHub user is not found.

## **Error Handling**

Errors are returned in the following format:

```json
{
    "status":  ${responseCode},
    "message":  ${whyHasItHappened}
}
```

For example, when a user tries to fetch data for a non-existent GitHub user:

```json
{
  "status": 404,
  "Message": "User not found"
}
```

### Testing

The application includes integration tests to validate its functionality against the acceptance criteria - happy path for existing GitHub user.
### License

This project is open-source. Feel free to fork, modify, and use as needed. Before using for commercial purposes, it's recommended to review any licensing constraints.

