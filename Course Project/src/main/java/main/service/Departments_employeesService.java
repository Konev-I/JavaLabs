package main.service;

import main.entity.Departments_employees;
import java.util.List;

public interface Departments_employeesService {
  List<Departments_employees> listDepEmp();
  Departments_employees getDepEmp(Integer id);
  Departments_employees addDepEmp(Departments_employees depEmp);

  void deleteAllDepEmp();
  void deleteDepEmp(int id);
}
