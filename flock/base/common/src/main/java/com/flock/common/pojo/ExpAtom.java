package com.flock.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * sql 的 where 条件解析过程的语句及其后连接符对象
 * @author Chunming_Wang
 */
@Data
public class ExpAtom implements Serializable {

    //语句
    private String statement;

    //连接符
    private String connector;

}
