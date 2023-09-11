package com.orange.lbworkshop

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping
class TestController {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Value("\${nginx.url}")
    private lateinit var nginxUrl: String
    
    @GetMapping("/delayed")
    fun delayedEndpoint(): String {
        TimeUnit.MINUTES.sleep(2)
        return "Response after delay"
    }

    @GetMapping("/forward-info")
    fun forwardInfo(): ResponseEntity<String> =
        restTemplate.getForEntity("$nginxUrl/actuator/info", String::class.java)
}