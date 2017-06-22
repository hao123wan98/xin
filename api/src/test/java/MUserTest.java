import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Created by guoyongshi on 17/5/25.
 */

//@WebMvcTest(controllers = UserController.class)
public class MUserTest extends BaseTestController {

    @Test
    public void Test() {

    }


    //    @Test
    public void login() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/muser/login");
//        mockRequest.header("token", this.token);
        mockRequest.param("email", "admin");
        mockRequest.param("pwd", "e10adc3949ba59abbe56e057f20f883e");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }
}


