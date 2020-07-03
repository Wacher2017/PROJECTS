package com.lms.sample.service;


import com.lms.sample.entity.Sample;

import java.util.List;

public interface SampleService {

    String addSample(Sample sample);

    Sample findOne();

    List<Sample> findList();

}
