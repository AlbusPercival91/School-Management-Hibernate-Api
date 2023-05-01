package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.*;
import ua.foxminded.springbootjdbc.school.dao.interfaces.StudentDao;
import ua.foxminded.springbootjdbc.school.dao.testdata.JPAGeneratorDataDao;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
    JPAStudentDao.class }))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test-container")
@Sql(scripts = { "/drop_data.sql", "/init_tables.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JPAStudentDaoTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StudentDao studentDao;

  private TestDataGenerator testData;

  @BeforeEach
  void setUp() {
    testData = new TestDataGenerator(new StudentMaker(), new GroupMaker(), new CourseMaker(),
        new JPAGeneratorDataDao(entityManager.getEntityManager()));
  }

//  @Test
//  @DisplayName("Should return true if number of students at course more than zero")
//  void testFindStudentsRelatedToCourse_ShouldReturnNameOfCourses() {
//    testData.createCourse();
//    testData.createStudent();
//    testData.createCourseStudentRelation();
//
//    for (String s : new CourseMaker().generateCourses()) {
//      long studentCount = studentDao.findStudentsRelatedToCourse(s).stream()
//          .filter(course -> course.toString().trim().contains(" ")).count();
//      Assertions.assertTrue(studentCount > 0);
//    }
//  }
//
//  @ParameterizedTest
//  @DisplayName("Should return 1 if 1 student assigned to one course")
//  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
//      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
//  void testAddStudentToTheCourse_ShouldReturnOneIfStudentAssigned(int studentId, String course) {
//    testData.createCourse();
//    testData.createStudent();
//    Assertions.assertEquals(1, studentDao.addStudentToTheCourse(studentId, course));
//  }
//
//  @Test
//  @DisplayName("Should return true when actual and inserted student are equals")
//  void testAddNewStudent_ShouldReturnEqualsWhenNewStudentCreated() {
//    Student student = new Student(4, "Harry", "Potter");
//    studentDao.addNewStudent(student);
//    entityManager.flush();
//    entityManager.clear();
//
//    Student actual = entityManager.find(Student.class, student.getId());
//    Assertions.assertEquals(student.toString(), actual.toString());
//  }
//
//  @Test
//  @DisplayName("Should return 1 if student deleted from DB")
//  void testDeleteStudentByID_ShouldReturnOneIfStudentRemoved() {
//    Student student = new Student(1, "Albus", "Dambldor");
//    entityManager.persist(student);
//    entityManager.flush();
//
//    int deleted = studentDao.deleteStudentByID(student.getId());
//    Assertions.assertEquals(1, deleted);
//  }
//
//  @Test
//  @DisplayName("Should return 1 if student deleted from course Table in DB")
//  void testRemoveStudentFromCourse_ShouldReturnOneIfStudentRemovedFromCourse() {
//    testData.createCourse();
//    testData.createStudent();
//    Student student = entityManager.find(Student.class, 17);
//    Course course = entityManager.find(Course.class, 3);
//
//    StudentCourseRelation relation = new StudentCourseRelation(student.getId(), course.getId());
//    entityManager.persist(relation);
//    entityManager.flush();
//
//    int deleted = studentDao.removeStudentFromCourse(student.getId(), course.getCourseName());
//    Assertions.assertEquals(1, deleted);
//  }

  @Test
  @DisplayName("Should return 1 if student updated")
  void testUpdateStudentById_ShouldReturnEqualsStringsWhenStudentUpdated() {
    Student student = new Student(4, "Harry", "Potter");
    entityManager.persist(student);
    entityManager.flush();

    Student updatedStudent = new Student(3, "Ron", "Wesley");
    studentDao.updateStudentById(student.getId(), updatedStudent);

    entityManager.clear();
    Student actualStudent = entityManager.find(Student.class, student.getId());

    String actual = actualStudent.getId() + " " + actualStudent.getGroupId() + " " + actualStudent.getFirstName() + " "
        + actualStudent.getLastName();
    String expected = student.getId() + " " + updatedStudent.getGroupId() + " " + updatedStudent.getFirstName() + " "
        + updatedStudent.getLastName();
    Assertions.assertEquals(expected, actual);
  }

//  @Test
//  @DisplayName("Should return all students")
//  void testShowAllStudents_ShouldReturnAllStudents() {
//    testData.createStudent();
//    List<Student> actual = studentDao.showAllStudents();
//    Assertions.assertEquals(200, actual.size());
//  }

}
