package com.yevhenpiven.bootstrapproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yevhenpiven.bootstrapproject.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}
