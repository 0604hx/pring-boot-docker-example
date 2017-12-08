package org.nerve.docker.jdbc

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet

@EnableAutoConfiguration
@SpringBootApplication
class JdbcApp

fun main(args: Array<String>) {
    SpringApplication.run(JdbcApp::class.java, *args)
}

@Component
class DBInitializer @Autowired() constructor(val jdbcTemplate: JdbcTemplate)
    : ApplicationListener<ApplicationReadyEvent>{

    val logger = LoggerFactory.getLogger(javaClass)

    override fun onApplicationEvent(p0: ApplicationReadyEvent) {
        logger.info("application is ready!")

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user " +
                "(id bigint(20) NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "age int," +
                "PRIMARY KEY (`id`)" +
                ")")
    }
}


class User{
    var id:Int=0
    var name:String?=null
    var age:Int=0

    constructor()

    constructor(name:String, age:Int):this(){
        this.name = name
        this.age = age
    }

    constructor(id: Int, name:String, age:Int):this(name, age){
        this.id = id
    }

    override fun toString(): String {
        return "User(id=$id, name=$name, age=$age)"
    }
}

class UserMapper : RowMapper<User>{
    override fun mapRow(rs: ResultSet, num: Int) =
            User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"))
}

@RestController
class UserController @Autowired() constructor(val jdbcTemplate: JdbcTemplate){

    @RequestMapping("list")
    fun list():List<User> =
            jdbcTemplate.query("SELECT id, name, age FROM user", UserMapper())

    @RequestMapping("add")
    fun add(name: String, age: Int)=
            jdbcTemplate.update("INSERT INTO user (name, age) VALUES (?,?)", name, age)

    @RequestMapping("delete/{id}")
    fun delete(@PathVariable("id") id:Int)=
            jdbcTemplate.update("DELETE FROM user where id=?", id)
}