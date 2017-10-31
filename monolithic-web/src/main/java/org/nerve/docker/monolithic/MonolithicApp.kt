package org.nerve.docker.monolithic

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.WebUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zengxm on 2017/10/30.
 */
@EnableAutoConfiguration
@SpringBootApplication
class MonolithicApp

fun main(args: Array<String>) {
    SpringApplication.run(MonolithicApp::class.java, *args)
}

@RestController
class IndexController {

    val logger = LoggerFactory.getLogger(javaClass)

    init {
        println("INIT IndexController ...")
    }

    @Value("\${spring.application.name:unknown}")
    lateinit var appName:String

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @RequestMapping("")
    fun whatIsTheTime():String {
        logger.info("service for request...")
        return "[$appName] ${dateFormatter.format(Date())}"
    }
}