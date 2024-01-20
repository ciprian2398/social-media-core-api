package com.socialmedia.coreapi

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

//@SpringBootApplication
class CoreApiApplication {

    static void main(String[] args) {
//        SpringApplication.run(CoreApiApplication, args)
        OllamaAPI ollamaAPI = new OllamaAPI("http://localhost:11434")
        ollamaAPI.setRequestTimeoutSeconds(120)
        var callback = ollamaAPI.askAsync("llama2", "say hello")
        println "yyyyyyy"
        callback.join()
        println callback.getResult()
        println "zzzzzzz"

    }
}
