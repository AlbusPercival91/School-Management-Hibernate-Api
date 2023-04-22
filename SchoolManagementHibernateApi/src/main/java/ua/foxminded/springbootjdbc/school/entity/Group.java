package ua.foxminded.springbootjdbc.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "groups", schema = "school")
public class Group {

  @Column(name = "group_name")
  private String groupName;

  public Group(String groupName) {
    this.groupName = groupName;
  }

}
