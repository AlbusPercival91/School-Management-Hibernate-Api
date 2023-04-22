package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "groups")
public class Group {
  private String groupName;

  public Group(String groupName) {
    this.groupName = groupName;
  }

}
