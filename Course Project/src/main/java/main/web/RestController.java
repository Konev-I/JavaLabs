package main.web;

import main.entity.Departments;
import main.entity.Departments_employees;
import main.entity.Employees;
import main.entity.Projects;
import main.exeptions.SomethingNotFoundExeption;
import main.service.DepartmentsService;
import main.service.Departments_employeesService;
import main.service.EmployeesService;
import main.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/accounting")
public class RestController {
  private DepartmentsService depSer;
  private EmployeesService empSer;
  private Departments_employeesService depEmpSer;
  private ProjectsService projSer;

// DEPARTMENT
  @GetMapping("/department")
  public ResponseEntity<List<Departments>> getAllDepartments(){
    List<Departments> tempList = depSer.listDepartments();
    return new ResponseEntity<>(tempList, HttpStatus.OK);
  }

  @GetMapping("/department/{id}")
  public ResponseEntity<Departments> getDepartment(@PathVariable("id") int id){
    try{
      return new ResponseEntity<>(depSer.getDepartment(id), HttpStatus.OK);
    }
    catch (SomethingNotFoundExeption exception)
    {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/department/add")
  public Departments addDepartment(String name){
    return depSer.addDepartment(new Departments(name));
  }

  @DeleteMapping("/department/delete")
  public void deleteAllDepartments() {
    depSer.deleteAllDepartments();
  }

  @DeleteMapping("/department/delete/{id}")
  public int deleteDepartment(@PathVariable int id) {
    try {
      depSer.deleteDepartment(id);
      return 0;
    }
    catch (Exception exception){
      return -1;
    }
  }

  @PutMapping("/department/update/{id}")
  public int updateDepartment(@PathVariable int id, @RequestBody String name){
    try {
      Departments depToUpdate = depSer.getDepartment(id);
      depToUpdate.setName(name);
      depSer.addDepartment(depToUpdate);
      return 0;
    }
    catch (Exception exception){
      return -1;
    }
  }

//EMPLOYEES
  @GetMapping("/employees")
  public ResponseEntity<List<Employees>> getAllEmployees(){
    List<Employees> tempList = empSer.listEmployees();
    return new ResponseEntity<>(tempList, HttpStatus.OK);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employees> getEmployee(@PathVariable("id") int id){
    try{
      return new ResponseEntity<>(empSer.getEmployees(id), HttpStatus.OK);
    }
    catch (SomethingNotFoundExeption exception)
    {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/employees/add")
  public int addDepartment(String fName, String lName, String pName, String position, String salary){
    empSer.addEmployees(new Employees(fName, lName, pName, position, Double.parseDouble(salary)));
    return 0;
  }

  @DeleteMapping("/employees/delete")
  public void deleteAllEmployees() {
    empSer.deleteAllEmployees();
  }

  @DeleteMapping("/employees/delete/{id}")
  public int deleteEmployee(@PathVariable int id) {
    try {
      empSer.deleteEmployee(id);
      return 0;
    }
    catch (Exception exception) {
      return -1;
    }
  }

  @PutMapping("/employees/update/{id}")
  public int updateEmployee(@PathVariable int id, String fName, String lName, String pName, String position, String salary){
    try {
      Employees empToUpdate = empSer.getEmployees(id);
      empToUpdate.setFirstName(fName);
      empToUpdate.setLastName(lName);
      empToUpdate.setPatherName(pName);
      empToUpdate.setPosition(position);
      empToUpdate.setSalary(Double.parseDouble(salary));
      empSer.addEmployees(empToUpdate);
      return 0;
    }
    catch (Exception exception)
    {
      return -1;
    }
  }

//DEPARTMENT_EMPLOYEES
  @GetMapping("/department_employees")
  public ResponseEntity<List<Departments_employees>> getAllDepartmentsEmployees(){
    List<Departments_employees> tempList = depEmpSer.listDepEmp();
    return new ResponseEntity<>(tempList, HttpStatus.OK);
  }

  @GetMapping("/department_employees/{id}")
  public ResponseEntity<Departments_employees> getDepEmp(@PathVariable("id") int id){
    try{
      return new ResponseEntity<>(depEmpSer.getDepEmp(id), HttpStatus.OK);
    }
    catch (SomethingNotFoundExeption exception)
    {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/department_employees/add")
  public int addDepartment(String depId, String empId){
    depEmpSer.addDepEmp(new Departments_employees(Integer.parseInt(depId), Integer.parseInt(empId)));
    return 0;
  }

  @DeleteMapping("/department_employees/delete")
  public void deleteAllDepEmp() {
    depEmpSer.deleteAllDepEmp();
  }

  @DeleteMapping("/department_employees/delete/{id}")
  public int deleteDepEmp(@PathVariable int id) {
    try {
      depEmpSer.deleteDepEmp(id);
      return 0;
    }
    catch (Exception e)
    {
      return -1;
    }
  }

  @PutMapping("/department_employees/update/{id}")
  public int updateDepEmp(@PathVariable int id, String depId, String empId){
    try {
      Departments_employees depEmpToUpdate = depEmpSer.getDepEmp(id);
      depEmpToUpdate.setDepartmentId(Integer.parseInt(depId));
      depEmpToUpdate.setEmployeeId(Integer.parseInt(empId));
      depEmpSer.addDepEmp(depEmpToUpdate);
      return 0;
    }
    catch (Exception e)
    {
      return -1;
    }
  }

  // Projects
  @GetMapping("/projects")
  public ResponseEntity<List<Projects>> getAllProjects(){
    List<Projects> tempList = projSer.listProjects();
    return new ResponseEntity<>(tempList, HttpStatus.OK);
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<Projects> getProject(@PathVariable("id") int id){
    try{
      return new ResponseEntity<>(projSer.getProjects(id), HttpStatus.OK);
    }
    catch (SomethingNotFoundExeption exception)
    {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public GregorianCalendar[] parseData(String[] arr){
    int year, month, day;
    GregorianCalendar[] calendars = {new GregorianCalendar(0000, 00, 00), new GregorianCalendar(0000, 00, 00), new GregorianCalendar(0000, 00, 00)};
    for (int i = 0; i < 3; ++i)
    {
      year = Integer.parseInt(arr[i].substring(0, 4));
      month = Integer.parseInt(arr[i].substring(5, 7));
      day = Integer.parseInt(arr[i].substring(8));
      calendars[i] = new GregorianCalendar(year, month, day);
    }
    return calendars;
  }

  @PostMapping(value = "/projects/add")
  public Projects addProject(String name, String cost, String depId, String dateBeg, String dateEnd, String dateEndReal){
    String[] arr = {dateBeg, dateEnd, dateEndReal};
    GregorianCalendar[] calendars = parseData(arr);
    return projSer.addProjects(new Projects(name, Double.parseDouble(cost), Integer.parseInt(depId), calendars[0], calendars[1], calendars[2]) );
  }

  @DeleteMapping("/projects/delete")
  public void deleteAllProjects() {
    projSer.deleteAllProjects();
  }

  @DeleteMapping("/projects/delete/{id}")
  public int deleteProject(@PathVariable int id) {
    try {
      projSer.deleteProject(id);
      return 0;
    }
    catch (Exception exception){
      return -1;
    }
  }

  @PutMapping("/projets/update/{id}")
  public int updateProjects(@PathVariable int id, String name, String cost, String depId, String dateBeg, String dateEnd, String dateEndReal){
    try {
      String[] arr = {dateBeg, dateEnd, dateEndReal};
      GregorianCalendar[] calendars = parseData(arr);
      Projects projToUpdate = projSer.getProjects(id);
      projToUpdate.setName(name);
      projToUpdate.setCost(Double.parseDouble(cost));
      projToUpdate.setDepId(Integer.parseInt(depId));
      projToUpdate.setDateBeg(calendars[0]);
      projToUpdate.setDateEnd(calendars[1]);
      projToUpdate.setDateEndReal(calendars[2]);
      projSer.addProjects(projToUpdate);
      return 0;
    }
    catch (Exception exception){
      return -1;
    }
  }

  @Autowired
  public void setDepartmentsService(DepartmentsService depSer) {
    this.depSer = depSer;
  }

  @Autowired
  public void setEmployeesService(EmployeesService empSer) {
    this.empSer = empSer;
  }

  @Autowired
  public void setDepartmentsEmployeesService(Departments_employeesService depEmpSer) {
    this.depEmpSer = depEmpSer;
  }

  @Autowired
  public void setProjectService(ProjectsService projectService)
  {
    this.projSer = projectService;
  }

}
