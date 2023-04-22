package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "students_courses_checkouts", schema = "school")
public class StudentCourseRelation {

  @Column(name = "student_id")
  private int studentId;

  @Column(name = "course_id")
  private int courseId;

  public StudentCourseRelation(int studentId, int courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

}
