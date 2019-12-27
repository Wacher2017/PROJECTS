package com.flock.common.anno;

import com.flock.common.enums.ProcessTypeEnum;

import java.lang.annotation.*;

/**
 * Handler注解，根据不同类型执行相应的handler
 * Created by Chunming_Wang on 2019/10/9.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandlerType {

    ProcessTypeEnum value();

}
