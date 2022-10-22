package org.mateoospina.domain.persistence;

import org.mateoospina.domain.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ToDoList,Long> {

}
