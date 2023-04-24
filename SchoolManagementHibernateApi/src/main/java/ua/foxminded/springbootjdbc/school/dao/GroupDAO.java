package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Repository
@Transactional
public class GroupDAO {

  @PersistenceContext
  private final EntityManager entityManager;

  public GroupDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(Integer students) {
    TypedQuery<Group> query = entityManager.createQuery(
        "SELECT g FROM Group g JOIN Student s ON g.id = s.groupId GROUP BY g HAVING COUNT(s) <= :students",
        Group.class);
    query.setParameter("students", students.longValue());
    return query.getResultList();
  }

  public int createGroup(Group group) {
    entityManager.persist(group);
    return group.getId();
  }

  public int editGroupName(String groupName, String newGroupName) {
    String jpql = "UPDATE Group c SET c.groupName = :newGroupName WHERE c.groupName = :groupName";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("newGroupName", newGroupName);
    query.setParameter("groupName", groupName);
    return query.executeUpdate();
  }

  public int deleteGroupByName(String groupName) {
    String jpql = "DELETE FROM Group c WHERE c.groupName = :groupName";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("groupName", groupName);
    return query.executeUpdate();
  }

  public List<Group> showAllGroups() {
    TypedQuery<Group> query = entityManager.createQuery("SELECT c FROM Group c", Group.class);
    return query.getResultList();
  }

}
