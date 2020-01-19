package com.auth.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chunming_Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {

    private int page;

    private int rows;

    private int total;

    private T data;

}
