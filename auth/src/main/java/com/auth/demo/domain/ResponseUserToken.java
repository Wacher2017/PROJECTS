package com.auth.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chunming_Wang
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {

    private String token;

    private UserDetail userDetail;

}
