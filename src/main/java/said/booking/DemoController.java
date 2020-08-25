package said.booking;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

   //FOR LOG4J2
    public static final Logger LOGGER = LogManager.getLogger(DemoController.class);

    //slf4j
    //public static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/api")
    public String hello(){
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("inside the DemoController");
        }

       LOGGER.debug("There are " + getUserCount() + " users logged in now.");
     //   LOGGER.debug("There are {} users logged in now. ",getUserCount());

        //log4j2
       //  LOGGER.debug("There are {} users logged in now. ",() -> getUserCount());

        return "API Rest";
    }

    private String getUserCount() {
        return "2147483647";
    }


    @RequestMapping("/test/{name}")
    public String greeting(@PathVariable String name) {
        LOGGER.debug("Request {}", new Exception(name));
        if (name.equalsIgnoreCase("test")) {
            throw new RuntimeException("Opps Exception raised....");
        }
        String response = "Hi " + name + " Welcome LOGGING";
        LOGGER.debug("Response {}", new Exception(response));
        return response;
    }
}
