package pro.savichev

import pro.savichev.db.DatabaseConfiguration
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

class AppInitializer: WebApplicationInitializer {
    override fun onStartup(servletContext: ServletContext?) {
        val ctx = AnnotationConfigWebApplicationContext()
        ctx.servletContext = servletContext
        ctx.register(DatabaseConfiguration::class.java)
        ctx.register(Configuration::class.java)
        val servlet = servletContext?.addServlet("dispatcher", DispatcherServlet(ctx))
        servlet?.setLoadOnStartup(1)
        servlet?.addMapping("/")
    }
}

