package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "course")
public class Course {
  private String courseName;
  private String courseDescription;

  public Course(String courseName, String courseDescription) {
    this.courseName = courseName;
    this.courseDescription = courseDescription;
  }

  public Course(String courseName) {
    this.courseName = courseName;
  }

}
