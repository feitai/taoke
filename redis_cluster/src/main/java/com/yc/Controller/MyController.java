package com.yc.Controller;

import com.yc.service.DistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private DistributedLockService lockService;

    @GetMapping("/doTask")
    public String doTaskWithLock() {
        lockService.performTaskWithLock();
        return "Task completed with lock.";
    }
}

