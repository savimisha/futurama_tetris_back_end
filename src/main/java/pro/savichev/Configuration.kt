package pro.savichev

import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["pro.savichev"])
open class Configuration {

    companion object {
        const val TAG = "FUTURAMA_TETRIS"
    }

    @Bean
    open fun getLogger(): Logger {
        return LoggerFactory.getLogger(TAG)
    }

    @Bean
    open fun getGson(): Gson {
        return Gson()
    }

}