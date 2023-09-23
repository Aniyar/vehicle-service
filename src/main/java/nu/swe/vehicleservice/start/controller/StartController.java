package nu.swe.vehicleservice.start.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
@RequiredArgsConstructor
public class StartController {

    @GetMapping()
    public String get() {
        return "Hello world ";
    }
}