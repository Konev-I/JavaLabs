package main.service;

import main.entity.Projects;
import main.exeptions.SomethingNotFoundExeption;
import main.repository.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsImpl implements ProjectsService {

  @Autowired
  private ProjectsRepo projRepo;

  @Override
  public List<Projects> listProjects() {
    return (List<Projects>) projRepo.findAll();
  }

  @Override
  public Projects getProjects(Integer id) {
    Optional<Projects> optionalDep = projRepo.findById(id);
    if (optionalDep.isPresent()){
      return optionalDep.get();
    } else {
      throw new SomethingNotFoundExeption("Project not found");
    }
  }

  @Override
  public Projects addProjects(Projects projects) {
    return projRepo.save(projects);
  }

  @Override
  public void deleteAllProjects() {
    projRepo.deleteAll();
  }

  @Override
  public void deleteProject(int id) {
    projRepo.deleteById(id);
  }
}
