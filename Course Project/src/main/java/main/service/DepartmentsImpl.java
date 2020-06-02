package main.service;

import main.entity.Departments;
import main.exeptions.SomethingNotFoundExeption;
import main.repository.DepartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsImpl implements DepartmentsService {

  @Autowired
  private DepartmentsRepo depRepo;

  @Override
  public List<Departments> listDepartments() {
    return (List<Departments>) depRepo.findAll();
  }

  @Override
  public Departments getDepartment(Integer id) {
    Optional<Departments> optionalDep = depRepo.findById(id);
    if (optionalDep.isPresent()){
      return optionalDep.get();
    } else {
      throw new SomethingNotFoundExeption("Departments not found");
    }
  }

  @Override
  public Departments addDepartment(Departments department) {
    return depRepo.save(department);
  }

  @Override
  public void deleteAllDepartments() {
    depRepo.deleteAll();
  }

  @Override
  public void deleteDepartment(int id) {
    depRepo.deleteById(id);
  }


}
