package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

@Repository
@Transactional
public class StudentDAO {

  @PersistenceContext
  private final EntityManager entityManager;

  public StudentDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public int addNewStudent(Student student) {
    entityManager.persist(student);
    return student.getId();
  }

  public int deleteStudentByID(int id) {
    Query query = entityManager.createQuery("DELETE FROM Student s WHERE s.id = :id");
    query.setParameter("id", id);
    return query.executeUpdate();
  }

  public List<Integer> getStudentID() {
    String jpql = "SELECT s.id FROM Student s";
    TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
    return query.getResultList();
  }

  public List<Student> findStudentsRelatedToCourse(String courseName) {
    String jpql = "SELECT s FROM Student s JOIN s.courses c WHERE c.name = :courseName";
    TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
    query.setParameter("courseName", courseName);
    return query.getResultList();
  }

  public int addStudentToTheCourse(Integer studentId, String courseName) {
    String jpql = "INSERT INTO StudentCourseRelation(student, course) " + "SELECT s, c FROM Student s, Course c "
        + "WHERE s.id = :studentId AND c.name = :courseName";
    TypedQuery<StudentCourseRelation> query = entityManager.createQuery(jpql, StudentCourseRelation.class);
    query.setParameter("studentId", studentId);
    query.setParameter("courseName", courseName);
    return query.executeUpdate();
  }

  public int removeStudentFromCourse(Integer studentId, String courseName) {
    String jpql = """
        DELETE FROM StudentCourseRelation sc
        WHERE sc.student.id = :studentId
        AND sc.course.id IN (SELECT c.id FROM Course c WHERE c.name = :courseName)
        """;
    Query query = entityManager.createQuery(jpql);
    query.setParameter("studentId", studentId);
    query.setParameter("courseName", courseName);
    return query.executeUpdate();
  }

  public int updateStudentById(Integer studentId, Student student) {
    String jpql = "UPDATE Student s SET s.groupId = :groupId, s.firstName = :firstName, s.lastName = :lastName WHERE s.id = :studentId";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("groupId", student.getGroupId());
    query.setParameter("firstName", student.getFirstName());
    query.setParameter("lastName", student.getLastName());
    query.setParameter("studentId", studentId);
    return query.executeUpdate();
  }

  public List<Student> showAllStudents() {
    TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
    return query.getResultList();
  }
}
