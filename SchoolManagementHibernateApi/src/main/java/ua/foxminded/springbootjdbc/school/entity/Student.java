package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "students")
public class Student {
  private Integer groupId;
  private String firstName;
  private String lastName;

  public Student(Integer groupId, String firstName, String lastName) {
    this.groupId = groupId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
