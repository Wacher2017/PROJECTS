package com.auth.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chunming_Wang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;

    private String name;

    private String nameZh;

}
