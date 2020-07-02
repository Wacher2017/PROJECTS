package com.demo.lms.controller;

import com.demo.lms.api.ResultVO;
import com.demo.lms.entity.Sample;
import com.demo.lms.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping
    public String addSample(@RequestBody @Valid Sample sample) {
        return sampleService.addSample(sample);
    }

    @GetMapping
    public Sample findSample() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setAccount("12345678");
        sample.setPassword("12345678");
        sample.setEmail("123@qq.com");
        // 注意，这里是直接返回的User类型，并没有用ResultVO进行包装
        return sample;
    }

    @GetMapping("/list")
    public List<Sample> findSampleList() {
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
        // 注意，这里是直接返回的User类型，并没有用ResultVO进行包装
        return result;
    }

    @GetMapping("/package")
    public ResultVO<List<Sample>> findSampleListVO() {
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
        return new ResultVO<>(result);
    }

}
