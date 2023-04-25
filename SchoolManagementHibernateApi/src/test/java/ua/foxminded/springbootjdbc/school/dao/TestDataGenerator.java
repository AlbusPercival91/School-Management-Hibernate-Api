package ua.foxminded.springbootjdbc.school.dao;

import java.util.Map;
import java.util.Set;

import ua.foxminded.springbootjdbc.school.dao.testdata.GeneratorDataRepository;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

public class TestDataGenerator {
  private final StudentMaker studentMaker;
  private final GroupMaker groupMaker;
  private final CourseMaker courseMaker;
  private final GeneratorDataRepository dataRepository;

  public TestDataGenerator(StudentMaker studentMaker, GroupMaker groupMaker, CourseMaker courseMaker,
      GeneratorDataRepository dataRepository) {
    this.studentMaker = studentMaker;
    this.groupMaker = groupMaker;
    this.courseMaker = courseMaker;
    this.dataRepository = dataRepository;
  }

  public void createStudent() {
    int i = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      dataRepository.createStudent(student);
    }
  }

  public void createGroup() {
    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      dataRepository.createGroup(group);
    }
  }

  public void createCourse() {
    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      dataRepository.createCourse(course);
    }
  }

  public void createCourseStudentRelation() {
    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        dataRepository.createCourseStudentRelation(scRelation);
      }
    }
  }

}
