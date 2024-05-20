package com.fct.serp.mappers;

import com.fct.serp.entities.OperateLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<OperateLog, Long> {
}
