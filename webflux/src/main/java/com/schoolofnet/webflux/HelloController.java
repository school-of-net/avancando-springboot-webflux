package com.schoolofnet.webflux;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 22/12/2021
 */
@RestController
public class HelloController {

    @GetMapping
    @ResponseBody
    public Publisher<String> sayHello() {
        return Mono.just("Hello World from Spring Webflux by School of net!");
    }
}
