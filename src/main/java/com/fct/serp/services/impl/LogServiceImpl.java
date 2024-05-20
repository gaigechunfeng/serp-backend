package com.fct.serp.services.impl;

import com.fct.serp.entities.OperateLog;
import com.fct.serp.mappers.LogRepository;
import com.fct.serp.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    @Autowired
    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(OperateLog operateLog) {

        logRepository.save(operateLog);
    }
}
