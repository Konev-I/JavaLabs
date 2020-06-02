package main.repository;

import main.entity.Projects;
import org.springframework.data.repository.CrudRepository;

public interface ProjectsRepo extends CrudRepository<Projects, Integer> {
}
