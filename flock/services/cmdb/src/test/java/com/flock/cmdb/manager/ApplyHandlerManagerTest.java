package com.flock.cmdb.manager;

import com.flock.cmdb.BaseTest;
import com.flock.common.enums.ProcessTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chunming_Wang on 2019/12/26.
 */
public class ApplyHandlerManagerTest extends BaseTest {

    @Autowired
    ApplyHandlerManager applyHandlerManager;

    @Test
    public void executeTest() {
        applyHandlerManager.execute(ProcessTypeEnum.HANDLER_ONE, "123");
        applyHandlerManager.execute(ProcessTypeEnum.HANDLER_TWO, null);
    }

}
