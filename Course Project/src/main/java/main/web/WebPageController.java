package main.web;


import main.entity.Departments;
import main.entity.Departments_employees;
import main.entity.Employees;
import main.entity.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebPageController {

  @Autowired
  RestController restController;

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/")
  public String mainPage() {
    return "mainPage";
  }

  // Departments

  @RequestMapping("/departments/main/{err}")
  public String showDep(@PathVariable String err, Model model) {
    model.addAttribute("error", err);
    return "departments";
  }

  @RequestMapping("/departments/all")
  public String allDepartments(Model model){
    List<Departments> list = restController.getAllDepartments().getBody();
    model.addAttribute("list", list);
    return "allDepartments";
  }

  @RequestMapping("/departments/{id}")
  public String departmentById(Model model, @PathVariable String id){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "departments";
    }
    Departments dep = restController.getDepartment(Integer.parseInt(id)).getBody();
    if (dep == null){
      model.addAttribute("err", -1);
      return "departments";
    }
    model.addAttribute("department", dep);
    return "department";
  }

  @RequestMapping("/departments/delete/{id}")
  public String deleteDepById(@PathVariable String id, Model model){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "departments";
    }
    int err = restController.deleteDepartment(Integer.parseInt(id));
    model.addAttribute("err", err);
    return "departments";
  }

  @RequestMapping("/departments/delete/")
  public String deleteDepById(Model model){
    restController.deleteAllDepartments();
    model.addAttribute("err", 0);
    return "departments";
  }

  @RequestMapping("/departments/update/{id}/{name}")
  public String updateDepById(@PathVariable String id, @PathVariable String name, Model model){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "departments";
    }
    int err = restController.updateDepartment(Integer.parseInt(id), name);
    model.addAttribute("err", err);
    return "departments";
  }

  @RequestMapping("/departments/add")
  public String addDep(
  @RequestParam(name="name", required=false, defaultValue="") String name, Model model
  ) {
    restController.addDepartment(name);
    model.addAttribute("err", 0);
    return "departments";
  }

  // Employees

  @RequestMapping("/employees/main/{err}")
  public String showEmp(@PathVariable String err, Model model) {
    model.addAttribute("error", err);
    return "employees";
  }

  @RequestMapping("/employees/all")
  public String allEmployees(Model model){
    List<Employees> list = restController.getAllEmployees().getBody();
    model.addAttribute("list", list);
    return "allEmployees";
  }

  @RequestMapping("/employees/{id}")
  public String employeeById(Model model, @PathVariable String id){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "employees";
    }
    Employees emp = restController.getEmployee(Integer.parseInt(id)).getBody();
    if (emp == null){
      model.addAttribute("err", -1);
      return "employees";
    }
    model.addAttribute("employee", emp);
    return "employee";
  }

  @RequestMapping("/employees/delete/{id}")
  public String deleteEmpById(@PathVariable String id, Model model){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "employees";
    }
    int err = restController.deleteEmployee(Integer.parseInt(id));
    model.addAttribute("err", err);
    return "employees";
  }

  @RequestMapping("/employees/delete/")
  public String deleteAllEmp(Model model){
    restController.deleteAllEmployees();
    model.addAttribute("err", 0);
    return "employees";
  }

  @RequestMapping("/employees/update/{id}")
  public String updateEmpById(
  @PathVariable String id,
  @RequestParam(name="fName", required=false, defaultValue="") String fName,
  @RequestParam(name="lName", required=false, defaultValue="") String lName,
  @RequestParam(name="pName", required=false, defaultValue="") String pName,
  @RequestParam(name="position", required=false, defaultValue="") String position,
  @RequestParam(name="salary", required=false, defaultValue="") String salary, Model model
  ){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "employees";
    }
    Employees oldEmp = restController.getEmployee(Integer.parseInt(id)).getBody();
    if (oldEmp == null){
      model.addAttribute("err", -1);
      return "employees";
    }
    if (fName.equals(""))
    {
      fName = oldEmp.getFirstName();
    }
    if (lName.equals(""))
    {
      lName = oldEmp.getLastName();
    }
    if (pName.equals(""))
    {
      pName = oldEmp.getPatherName();
    }
    if (position.equals(""))
    {
      position = oldEmp.getPosition();
    }
    if (salary.equals(""))
    {
      salary = oldEmp.getSalary().toString();
    }
    try{
      Double.parseDouble(salary);
    }
    catch (Exception exception){
      model.addAttribute("err", -3);
      return "employees";
    }
    int err = restController.updateEmployee(Integer.parseInt(id), fName, lName, pName, position, salary);
    model.addAttribute("err", err);
    return "employees";
  }

  @RequestMapping("/employees/add")
  public String addEmp(
  @RequestParam(name="fName", required=false, defaultValue="") String fName,
  @RequestParam(name="lName", required=false, defaultValue="") String lName,
  @RequestParam(name="pName", required=false, defaultValue="") String pName,
  @RequestParam(name="position", required=false, defaultValue="") String position,
  @RequestParam(name="salary", required=false, defaultValue="") String salary, Model model
  ) {
    try{
      Double.parseDouble(salary);
    }
    catch (Exception exception){
      model.addAttribute("err", -3);
      return "employees";
    }
    restController.addDepartment(fName, lName, pName, position, salary);
    model.addAttribute("err", 0);
    return "employees";
  }


  // Departments & employees

  @RequestMapping("/depEmps/main/{err}")
  public String showDepEmp(@PathVariable String err, Model model) {
    model.addAttribute("error", err);
    return "depEmps";
  }

  @RequestMapping("/depEmps/all")
  public String allDepEmps(Model model){
    List<Departments_employees> list = restController.getAllDepartmentsEmployees().getBody();
    model.addAttribute("list", list);
    return "allDepEmps";
  }

  @RequestMapping("/depEmps/{id}")
  public String depEmpById(Model model, @PathVariable String id){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "depEmps";
    }
    Departments_employees depEmp = restController.getDepEmp(Integer.parseInt(id)).getBody();
    if (depEmp == null){
      model.addAttribute("err", -1);
      return "depEmps";
    }
    model.addAttribute("depEmp", depEmp);
    return "depEmp";
  }

  @RequestMapping("/depEmps/delete/{id}")
  public String deleteDepEmpById(@PathVariable String id, Model model){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "depEmps";
    }
    int err = restController.deleteDepEmp(Integer.parseInt(id));
    model.addAttribute("err", err);
    return "depEmps";
  }

  @RequestMapping("/depEmps/delete/")
  public String deleteDepEmpById(Model model){
    restController.deleteAllDepEmp();
    model.addAttribute("err", 0);
    return "employees";
  }

  @RequestMapping("/depEmps/update/{id}")
  public String updateDepEmpById(
  @PathVariable String id,
  @RequestParam(name="depId", required=false, defaultValue="") String depId,
  @RequestParam(name="empId", required=false, defaultValue="") String empId, Model model
  ){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "depEmps";
    }
    try {
      Integer.parseInt(depId);
      Integer.parseInt(empId);
    }
    catch (Exception e)
    {
      model.addAttribute("err", -3);
      return "depEmps";
    }
    Departments_employees oldDepEmp = restController.getDepEmp(Integer.parseInt(id)).getBody();
    if (oldDepEmp == null){
      model.addAttribute("err", -1);
      return "depEmps";
    }
    if (depId.equals(""))
    {
      depId = oldDepEmp.getDepartmentId().toString();
    }
    if (empId.equals(""))
    {
      empId = oldDepEmp.getEmployeeId().toString();
    }
    int err = restController.updateDepEmp(Integer.parseInt(id), depId, empId);
    model.addAttribute("err", err);
    return "depEmps";
  }

  @RequestMapping("/depEmps/add")
  public String addDepEmp(
  @RequestParam(name="depId", required=false, defaultValue="") String depId,
  @RequestParam(name="empId", required=false, defaultValue="") String empId, Model model
  ) {
    try {
      Integer.parseInt(depId);
      Integer.parseInt(empId);
    }
    catch (Exception e)
    {
      model.addAttribute("err", -3);
      return "depEmps";
    }
    restController.addDepartment(depId, empId);
    model.addAttribute("err", 0);
    return "depEmps";
  }

  // Projects

  @RequestMapping("/projects/main/{err}")
  public String showProj(@PathVariable String err, Model model) {
    model.addAttribute("error", err);
    return "projects";
  }

  @RequestMapping("/projects/all")
  public String allProjects(Model model){
    List<Projects> list = restController.getAllProjects().getBody();
    model.addAttribute("list", list);
    return "allProjects";
  }

  @RequestMapping("/projects/{id}")
  public String projectsById(Model model, @PathVariable String id){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "projects";
    }
    Projects proj = restController.getProject(Integer.parseInt(id)).getBody();
    if (proj == null){
      model.addAttribute("err", -1);
      return "projects";
    }
    model.addAttribute("project", proj);
    return "project";
  }

  @RequestMapping("/projects/delete/{id}")
  public String deleteProjById(@PathVariable String id, Model model){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "projects";
    }
    int err = restController.deleteProject(Integer.parseInt(id));
    model.addAttribute("err", err);
    return "projects";
  }

  @RequestMapping("/projects/delete/")
  public String deleteAllProj(Model model){
    restController.deleteAllProjects();
    model.addAttribute("err", 0);
    return "projects";
  }

  public int dateChecker(String date){
    try {
      int year, month, day;
      year = Integer.parseInt(date.substring(0, 4));
      month = Integer.parseInt(date.substring(5, 7));
      day = Integer.parseInt(date.substring(8));
      if (month < 0 || month > 11 || day < 1 || day > 31 || year < 1900 || year > 9999){
        return -1;
      }
      return 0;
    }
    catch (Exception e){
      return -1;
    }
  }

  public String dateCorrector(String date){
    int year, month, day;
    year = Integer.parseInt(date.substring(0, 4));
    month = Integer.parseInt(date.substring(5, 7));
    day = Integer.parseInt(date.substring(8));
    month -= 1;
    if (month < 10){
      return year+"/0"+month+"/"+day;
    }
    return year+"/"+month+"/"+day;
  }

  @RequestMapping("/projects/update/{id}")
  public String updateProjById(
  @PathVariable String id,
  @RequestParam(name="name", required=false, defaultValue="") String name,
  @RequestParam(name="cost", required=false, defaultValue="") String cost,
  @RequestParam(name="depId", required=false, defaultValue="") String depId,
  @RequestParam(name="dateBeg", required=false, defaultValue="") String dateBeg,
  @RequestParam(name="dateEnd", required=false, defaultValue="") String dateEnd,
  @RequestParam(name="dateEndReal", required=false, defaultValue="") String dateEndReal, Model model
  ){
    try{
      Integer.parseInt(id);
    }
    catch (Exception exception){
      model.addAttribute("err", -2);
      return "projects";
    }
    Projects oldProj = restController.getProject(Integer.parseInt(id)).getBody();
    if (oldProj == null){
      model.addAttribute("err", -1);
      return "projects";
    }
    if (name.equals(""))
    {
      name = oldProj.getName();
    }
    if (cost.equals(""))
    {
      cost = oldProj.getCost().toString();
    }
    if (depId.equals(""))
    {
      depId = oldProj.getDepId().toString();
    }
    if (dateBeg.equals(""))
    {
      dateBeg = oldProj.getDateBeg();
    }
    else {
      dateBeg = dateCorrector(dateBeg);
    }
    if (dateEnd.equals(""))
    {
      dateEnd = oldProj.getDateEnd();
    }
    else {
      dateEnd = dateCorrector(dateEnd);
    }
    if (dateEndReal.equals(""))
    {
      dateEndReal = oldProj.getDateEndReal();
    }
    else {
      dateEndReal = dateCorrector(dateEndReal);
    }
    if (!(dateChecker(dateBeg) == 0 && dateChecker(dateEnd) == 0 && dateChecker(dateEndReal) == 0))
    {
      model.addAttribute("err", -4);
      return "projects";
    }
    try{
      Double.parseDouble(cost);
    }
    catch (Exception exception){
      model.addAttribute("err", -3);
      return "projects";
    }
    int err = restController.updateProjects(Integer.parseInt(id), name, cost, depId, dateBeg, dateEnd, dateEndReal);
    model.addAttribute("err", err);
    return "projects";
  }

  @RequestMapping("/projects/add")
  public String addEmp(
  @RequestParam(name="name", required=false, defaultValue="") String name,
  @RequestParam(name="cost", required=false, defaultValue="") String cost,
  @RequestParam(name="depId", required=false, defaultValue="") String depId,
  @RequestParam(name="dateBeg", required=false, defaultValue="") String dateBeg,
  @RequestParam(name="dateEnd", required=false, defaultValue="") String dateEnd,
  @RequestParam(name="dateEndReal", required=false, defaultValue="") String dateEndReal, Model model
  ) {
    try{
      Double.parseDouble(cost);
    }
    catch (Exception exception){
      model.addAttribute("err", -3);
      return "projects";
    }
    restController.addProject(name, cost, depId, dateBeg, dateEnd, dateEndReal);
    model.addAttribute("err", 0);
    return "projects";
  }
}