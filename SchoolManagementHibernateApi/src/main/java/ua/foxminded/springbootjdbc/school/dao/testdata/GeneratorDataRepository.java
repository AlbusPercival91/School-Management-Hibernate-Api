package ua.foxminded.springbootjdbc.school.dao.testdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

@Repository
@Transactional
public class GeneratorDataRepository {

  @PersistenceContext
  private final EntityManager entityManager;

  public GeneratorDataRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void createStudent(Student student) {
    entityManager.persist(student);
  }

  public void createGroup(Group group) {
    entityManager.persist(group);
  }

  public void createCourse(Course course) {
    entityManager.persist(course);
  }

  public void createCourseStudentRelation(StudentCourseRelation scRelation) {
    entityManager.persist(scRelation);
  }

  public int rowsCount() {
    String sql = """
                  SELECT SUM (COUNT) FROM (
        SELECT COUNT(*) AS COUNT FROM school.students
        UNION ALL
        SELECT COUNT(*) AS COUNT FROM school.groups
        UNION ALL
        SELECT COUNT(*) AS COUNT FROM school.course
        UNION ALL
        SELECT COUNT(*) AS COUNT FROM school.students_courses_checkouts
        ) AS counts;
                  """;
    Query query = entityManager.createNativeQuery(sql);
    return ((Number) query.getSingleResult()).intValue();
  }

}
