package main.service;

import main.entity.Projects;

import java.util.List;

public interface ProjectsService {
  List<Projects> listProjects();
  Projects getProjects(Integer id);
  Projects addProjects(Projects projects);

  void deleteAllProjects();
  void deleteProject(int id);
}
