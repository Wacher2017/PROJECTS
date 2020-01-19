package com.auth.demo.exception;

import com.auth.demo.model.ResultJson;
import lombok.Getter;

/**
 * @author Chunming_Wang
 */
@Getter
public class CustomException extends RuntimeException {

    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }

}
