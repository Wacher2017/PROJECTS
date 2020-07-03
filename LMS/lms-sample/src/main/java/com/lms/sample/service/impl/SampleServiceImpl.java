package com.lms.sample.service.impl;

import com.lms.sample.entity.Sample;
import com.lms.sample.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public String addSample(Sample sample) {
        return "success";
    }

}
