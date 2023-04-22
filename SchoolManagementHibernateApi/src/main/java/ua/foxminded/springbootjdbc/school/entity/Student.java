package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "students", schema = "school")
public class Student {

  @Column(name = "group_id")
  private Integer groupId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  public Student(Integer groupId, String firstName, String lastName) {
    this.groupId = groupId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
