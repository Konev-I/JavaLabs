package main.entity;

import javax.persistence.*;

@Entity
public class Departments {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  public Departments() {
  }

  public Departments(String name) {
    this.name = name;
  }

  public Departments(Integer id, String name){
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "DepartmentsRepo{" +
    "id=" + id +
    ", name='" + name + '\'' +
    '}';
  }
}
