package com.demo.lms.service.impl;

import com.demo.lms.entity.Sample;
import com.demo.lms.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public String addSample(Sample sample) {
        return "success";
    }

}
