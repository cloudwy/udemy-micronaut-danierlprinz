package com.wy.udemy;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


// First Application
//@Controller("/hello")
//public class HelloWorldController {
//    @Get(produces = MediaType.TEXT_PLAIN)
//    public String helloWorld(){
//        return "Hello World!";
//    }
//}


@Controller("/hello")
public class HelloWorldController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    @Inject
    private final MyService service;
    private final String helloFromConfig;
    private final HelloWorldTranslationConfig translationConfig;

    public HelloWorldController(MyService service,
                                @Property(name = "hello.world.message") String helloFromConfig,
                                HelloWorldTranslationConfig translationConfig){
        this.helloFromConfig = helloFromConfig;
        this.service = service;
        this.translationConfig = translationConfig;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld(){
        LOG.debug("Called the hello World API.");
        return service.helloFromService();
    }

    @Get(uri = "/config", produces = MediaType.TEXT_PLAIN)
    public String helloCinfig(){
        LOG.debug("Return Hello From Config Message: {}", helloFromConfig);
        return helloFromConfig;
    }

    @Get(uri = "/translation", produces = MediaType.APPLICATION_JSON)
    public HelloWorldTranslationConfig helloTranslation() {
        return translationConfig;
    }
}
