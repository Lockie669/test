package com.jack.service.impl;

import com.jack.dao.SysLogDao;
import com.jack.domain.SysLog;
import com.jack.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save (SysLog sysLog) {

        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll ( ) throws Exception{

        return sysLogDao.findAll();
    }
}
