package com.flock.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * sql 的 where 条件表达式解析结构
 * @author Chunming_Wang
 */
@Data
public class ExpStatement implements Serializable {

    //没有括号级，且通过 and 或 or 逻辑符连接的表达式语句
    private ExpAtom single;

    //当前层一级括号外的表达式集合
    private List<ExpAtom> outSide;

    //当前层一级括号内的表达式集合
    private List<ExpAtom> inSide;

    //当前层括号中解析的下一层结构
    private List<ExpStatement> next;

}
