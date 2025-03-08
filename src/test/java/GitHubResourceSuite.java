import com.githubappi.model.GitHubRepo;
import com.githubappi.service.GitHubService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class GitHubResourceSuite {
    @Inject
    GitHubService gitHubService;

    @Test
    public void shouldReturnRepositoriesForValidUser() {
        // Given: użytkownik o nazwie "pelemistrz" istnieje w GitHub, posiada repozytoria
        String user = "pelemistrz";

        // When
        List<GitHubRepo> repositories = gitHubService.getRepositories(user).await().indefinitely();

        //Then
        assertNotNull(repositories);
    }


}
