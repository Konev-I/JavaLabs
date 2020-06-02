package main.service;

import main.entity.Employees;

import java.util.List;

public interface EmployeesService {
  List<Employees> listEmployees();
  Employees getEmployees(Integer id);
  Employees addEmployees(Employees emp);

  void deleteAllEmployees();
  void deleteEmployee(int id);
}
