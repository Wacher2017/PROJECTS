package com.lms.sample.service.impl;

import com.lms.sample.entity.Sample;
import com.lms.sample.service.SampleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public String addSample(Sample sample) {
        return "success";
    }

    @Override
    public Sample findOne() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setAccount("12345678");
        sample.setPassword("12345678");
        sample.setEmail("123@qq.com");
        return sample;
    }

    @Override
    public List<Sample> findList() {
        List<Sample> result = new ArrayList<>();
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setAccount("12345678");
        sample.setPassword("12345678");
        sample.setEmail("123@qq.com");
        result.add(sample);
        sample = new Sample();
        sample.setId(2L);
        sample.setAccount("87654321");
        sample.setPassword("87654321");
        sample.setEmail("321@qq.com");
        result.add(sample);
        return result;
    }

}
