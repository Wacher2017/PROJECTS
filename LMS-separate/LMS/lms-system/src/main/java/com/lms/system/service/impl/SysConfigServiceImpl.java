package com.lms.system.service.impl;

import com.lms.system.domain.SysConfig;
import com.lms.system.service.ISysConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置 服务层实现
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Override
    public SysConfig selectConfigById(Long configId) {
        return null;
    }

    @Override
    public String selectConfigByKey(String configKey) {
        return null;
    }

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return null;
    }

    @Override
    public int insertConfig(SysConfig config) {
        return 0;
    }

    @Override
    public int updateConfig(SysConfig config) {
        return 0;
    }

    @Override
    public int deleteConfigByIds(String ids) {
        return 0;
    }

    @Override
    public void clearCache() {

    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        return null;
    }

}
