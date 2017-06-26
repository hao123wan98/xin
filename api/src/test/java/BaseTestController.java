import com.xin.SpringBootApiApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)

@WebAppConfiguration
@SpringBootTest(classes = SpringBootApiApplication.class)

public class BaseTestController {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;
    //
    public String token = "f39b524fec84e94984a7da118e96804d56d874f2";

    @Before
    public void setUp() {
        /* 启动日志 */
        // LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // JoranConfigurator configurator = new JoranConfigurator();
        // configurator.setContext(lc);
        // System.setProperty("hbApp.root", "src/test/java/logs");
        // lc.reset();
        // try {
        // configurator.doConfigure("src/main/resources/logback.xml");
        // } catch (JoranException e) {
        // e.printStackTrace();
        // }
        // lc.start();
        // StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


}
