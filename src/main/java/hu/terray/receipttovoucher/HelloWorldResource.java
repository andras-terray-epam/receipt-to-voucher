package hu.terray.receipttovoucher;

/**
 * Created by andrasterray on 2/4/17.
 */

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final GuiceTest guiceTest;

    @Inject
    public HelloWorldResource(@Named("template") String template, @Named("defaultName") String defaultName, GuiceTest guiceTest) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.guiceTest = guiceTest;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        guiceTest.logSomething();
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}