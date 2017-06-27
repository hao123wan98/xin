import com.xin.user.controller.UserController;
import com.xin.user.service.TokenService;
import com.xin.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Created by guoyongshi on 17/5/25.
 */

//@WebMvcTest(controllers = UserController.class)
public class UserTest extends BaseTestController {
    @Autowired
    TokenService tokenService;

    @Test
    public void Test() {
//        tokenService.getUserId(this.token);

    }

    //    @Test
    public void sendSms() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/smscode/send");
        mockRequest.param("mobile", "13699274927");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //        @Test
    public void register() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/register");
        mockRequest.param("mobile", "13699274927");
        mockRequest.param("smscode", "1105");
        mockRequest.param("pwd", "123456");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //        @Test
    public void login() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/login");
        mockRequest.param("mobile", "13699274927");
        mockRequest.param("pwd", "123456");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void getInfo() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/get");
        mockRequest.header("token", this.token);

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void setInfo() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/info/set");
        mockRequest.header("token", this.token);
        mockRequest.param("gender", "1");
        mockRequest.param("name", "sgy");
        mockRequest.param("email", "guoyong.shi@zhenhr.com");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void sendCVEmail() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/send/cvmail");
        mockRequest.header("token", this.token);

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }
}


