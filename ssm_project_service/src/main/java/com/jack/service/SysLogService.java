package com.jack.service;

import com.jack.domain.SysLog;

import java.util.List;

public interface SysLogService {

    public void save (SysLog sysLog);

    List<SysLog> findAll ( ) throws Exception;
}
