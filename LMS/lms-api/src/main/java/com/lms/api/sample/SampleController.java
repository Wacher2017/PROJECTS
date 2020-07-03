package com.lms.api.sample;

import com.lms.common.api.ResultVO;
import com.lms.sample.entity.Sample;
import com.lms.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        // 注意，这里是直接返回的User类型，并没有用ResultVO进行包装
        return sampleService.findOne();
    }

    @GetMapping("/list")
    public List<Sample> findSampleList() {
        // 注意，这里是直接返回的User类型，并没有用ResultVO进行包装
        return sampleService.findList();
    }

    @GetMapping("/package")
    public ResultVO<List<Sample>> findSampleListVO() {
        List<Sample> result = sampleService.findList();
        return new ResultVO<>(result);
    }

}
