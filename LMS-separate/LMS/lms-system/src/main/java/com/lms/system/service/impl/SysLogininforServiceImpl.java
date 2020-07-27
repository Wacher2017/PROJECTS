package com.lms.system.service.impl;

import com.lms.system.domain.SysLogininfor;
import com.lms.system.service.ISysLogininforService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService {

    @Override
    public void insertLogininfor(SysLogininfor logininfor) {

    }

    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        return null;
    }

    @Override
    public int deleteLogininforByIds(String ids) {
        return 0;
    }

    @Override
    public void cleanLogininfor() {

    }
}
