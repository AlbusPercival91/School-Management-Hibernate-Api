package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Course;

@Repository
@Transactional
public class CourseDAO {

  @PersistenceContext
  private final EntityManager entityManager;

  public CourseDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Course> findCoursesWithLessOrEqualsStudents(int students) {
    TypedQuery<Course> query = entityManager
        .createQuery("SELECT c FROM Course c JOIN c.students s GROUP BY c HAVING COUNT(s) <= :students", Course.class);
    query.setParameter("students", students);
    return query.getResultList();
  }

  public int createCourse(Course course) {
    entityManager.persist(course);
    return course.getId();
  }

  public int editCourseNameAndDescription(String courseName, String newCourseName, String newDescription) {
    String jpql = "UPDATE Course c SET c.courseName = :newCourseName, c.courseDescription = :newDescription WHERE c.courseName = :courseName";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("newCourseName", newCourseName);
    query.setParameter("newDescription", newDescription);
    query.setParameter("courseName", courseName);
    return query.executeUpdate();
  }

  public int deleteCourseByName(String courseName) {
    String jpql = "DELETE FROM Course c WHERE c.courseName = :courseName";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("courseName", courseName);
    return query.executeUpdate();
  }


  public List<Course> showAllCourses() {
    TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
    return query.getResultList();
  }
}
