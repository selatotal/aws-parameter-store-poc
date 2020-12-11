package br.com.selat.awsparameterstorepoc.impl.rest.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context
						= new AnnotationConfigWebApplicationContext();

		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.setInitParameter(
						"contextConfigLocation", "br.com.selat.awsparameterstorepoc");

	}
}
