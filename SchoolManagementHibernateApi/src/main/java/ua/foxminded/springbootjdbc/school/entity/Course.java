package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "course", schema = "school")
public class Course {

  @Column(name = "course_name")
  private String courseName;

  @Column(name = "course_description")
  private String courseDescription;

  public Course(String courseName, String courseDescription) {
    this.courseName = courseName;
    this.courseDescription = courseDescription;
  }

  public Course(String courseName) {
    this.courseName = courseName;
  }

}
