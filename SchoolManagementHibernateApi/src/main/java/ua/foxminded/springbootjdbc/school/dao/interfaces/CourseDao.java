package ua.foxminded.springbootjdbc.school.dao.interfaces;

import java.util.List;
import ua.foxminded.springbootjdbc.school.entity.Course;

public interface CourseDao {

  List<Course> findCoursesWithLessOrEqualsStudents(int students);

  int createCourse(Course course);

  int editCourseNameAndDescription(String courseName, String newCourseName, String newDescription);

  int deleteCourseByName(String courseName);

  List<Course> showAllCourses();
}
