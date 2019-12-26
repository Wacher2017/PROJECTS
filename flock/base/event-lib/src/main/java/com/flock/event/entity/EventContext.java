package com.flock.event.entity;

import java.io.Serializable;

/**
 * 事件对象定义
 * Created by Chunming_Wang on 2019/12/20.
 */
public class EventContext implements Serializable {

    private String tenant;

    private Integer  tenantId;

    private String  user;

    private String  userId;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
