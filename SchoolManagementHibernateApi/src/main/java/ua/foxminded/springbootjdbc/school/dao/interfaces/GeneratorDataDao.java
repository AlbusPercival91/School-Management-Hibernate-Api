package ua.foxminded.springbootjdbc.school.dao.interfaces;

import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

public interface GeneratorDataDao {

  void createStudent(Student student);

  void createGroup(Group group);

  void createCourse(Course course);

  void createCourseStudentRelation(StudentCourseRelation scRelation);

  int rowsCount();
}
