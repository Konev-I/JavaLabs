package main.service;

import main.entity.Employees;
import main.exeptions.SomethingNotFoundExeption;
import main.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesImpl implements EmployeesService {

  @Autowired
  private EmployeesRepo empRepo;

  @Override
  public List<Employees> listEmployees() {
    return (List<Employees>) empRepo.findAll();
  }

  @Override
  public Employees getEmployees(Integer id) {
    Optional<Employees> optionalEmp = empRepo.findById(id);
    if (optionalEmp.isPresent()){
      return optionalEmp.get();
    } else {
      throw new SomethingNotFoundExeption("Employees not found");
    }
  }

  @Override
  public Employees addEmployees(Employees emp) {
    return empRepo.save(emp);
  }

  @Override
  public void deleteAllEmployees() {
    empRepo.deleteAll();
  }

  @Override
  public void deleteEmployee(int id) {
    empRepo.deleteById(id);
  }
}
