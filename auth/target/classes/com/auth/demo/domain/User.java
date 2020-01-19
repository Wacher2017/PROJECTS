package com.auth.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * @author Chunming_Wang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty(value = "用户名", required = true)
    @Size(min=6, max=20)
    private String name;
    @ApiModelProperty(value = "密码", required = true)
    @Size(min=8, max=20)
    private String password;

}
