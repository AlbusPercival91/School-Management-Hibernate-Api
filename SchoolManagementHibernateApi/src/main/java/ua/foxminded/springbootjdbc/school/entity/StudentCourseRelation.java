package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "students_courses_checkouts")
public class StudentCourseRelation {
  private int studentId;
  private int courseId;

  public StudentCourseRelation(int studentId, int courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

}
