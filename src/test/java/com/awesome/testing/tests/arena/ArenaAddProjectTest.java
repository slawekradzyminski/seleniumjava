package com.awesome.testing.tests.arena;

import com.awesome.testing.generator.dto.Project;
import com.awesome.testing.pages.arena.ArenaAddProjectPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.ProjectProvider.getRandomProject;
import static org.assertj.core.api.Assertions.assertThat;

public class ArenaAddProjectTest extends AbstractArenaLoggedInTest {

    private ArenaAddProjectPage arenaAddProjectPage;

    @BeforeEach
    public void setUp() {
        driver.get("http://demo.testarena.pl/administration/add_project");
        arenaAddProjectPage = new ArenaAddProjectPage(driver);
    }

    @Test
    public void shouldAddNewProject() {
        Project project = getRandomProject();
        arenaAddProjectPage.addProject(project)
                .verifySuccessMessage();
        // fires screenshot
        assertThat(2).isEqualTo(3);
    }

}
