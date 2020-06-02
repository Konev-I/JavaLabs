package main.service;

import main.entity.Departments_employees;
import main.exeptions.SomethingNotFoundExeption;
import main.repository.Departments_employeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Departments_employeesImpl implements Departments_employeesService {

  @Autowired
  private Departments_employeesRepo depEmpRepo;

  @Override
  public List<Departments_employees> listDepEmp() {
    return (List<Departments_employees>) depEmpRepo.findAll();
  }

  @Override
  public Departments_employees getDepEmp(Integer id) {
    Optional<Departments_employees> optionalDepEmp = depEmpRepo.findById(id);
    if (optionalDepEmp.isPresent()){
      return optionalDepEmp.get();
    } else {
      throw new SomethingNotFoundExeption("Departments_Employees not found");
    }
  }

  @Override
  public Departments_employees addDepEmp(Departments_employees depEmp) {
    return depEmpRepo.save(depEmp);
  }

  @Override
  public void deleteAllDepEmp() {
    depEmpRepo.deleteAll();
  }

  @Override
  public void deleteDepEmp(int id) {
    depEmpRepo.deleteById(id);
  }
}
