package main.service;

import main.entity.Departments;

import java.util.List;

public interface DepartmentsService {
  List<Departments> listDepartments();
  Departments getDepartment(Integer id);
  Departments addDepartment(Departments department);

  void deleteAllDepartments();
  void deleteDepartment(int id);
}
