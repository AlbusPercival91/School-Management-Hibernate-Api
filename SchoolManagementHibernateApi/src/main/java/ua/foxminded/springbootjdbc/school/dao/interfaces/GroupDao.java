package ua.foxminded.springbootjdbc.school.dao.interfaces;

import java.util.List;
import ua.foxminded.springbootjdbc.school.entity.Group;

public interface GroupDao {

  public List<Group> findGroupsWithLessOrEqualsStudents(Integer students);

  public int createGroup(Group group);

  public int editGroupName(String groupName, String newGroupName);

  public int deleteGroupByName(String groupName);

  public List<Group> showAllGroups();

}
