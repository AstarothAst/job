package com.example.job.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpeedController {

    int speed = 7;

    @GetMapping("/faster")
    public Integer faster() {
        speed++;
        if (speed > 9) {
            speed = 9;
        }
        return speed;
    }

    @GetMapping("/slower")
    public Integer slower() {
        speed--;
        if (speed < 1) {
            speed = 1;
        }
        return speed;
    }

    public Integer getSpeed(){
        return speed;
    }
}
