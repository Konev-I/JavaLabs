package main.entity;

import javax.persistence.*;

@Entity
public class Employees {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id = 0;

  @Column(name = "first_name", nullable = false, length = 20)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 20)
  private String lastName;

  @Column(name = "pather_name", nullable = false, length = 20)
  private String patherName;

  @Column(name = "position", nullable = false, length = 50)
  private String position;

  @Column(name = "salary", nullable = false)
  private double salary;

  public Employees(){
  }

  public Employees(String fName, String lName, String pName, String pos, Double salary){
    this.firstName = fName;
    this.lastName = lName;
    this.patherName = pName;
    this.position = pos;
    this.salary = salary;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPatherName() {
    return patherName;
  }

  public void setPatherName(String patherName) {
    this.patherName = patherName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Employees{" +
    "id=" + id +
    ", firstName='" + firstName + '\'' +
    ", lastName='" + lastName + '\'' +
    ", patherName='" + patherName + '\'' +
    ", position='" + position + '\'' +
    ", salary=" + salary +
    '}';
  }
}
