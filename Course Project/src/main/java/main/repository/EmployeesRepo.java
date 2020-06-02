package main.repository;

import main.entity.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepo extends CrudRepository<Employees, Integer> {
}
