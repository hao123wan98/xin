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


    //        @Test
    public void login() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/muser/login");
//        mockRequest.header("token", this.token);
        mockRequest.param("email", "admin");
        mockRequest.param("pwd", "e10adc3949ba59abbe56e057f20f883e");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void changePwd() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/muser/pwd/change");
        mockRequest.header("token", "bf32a052a85016bd02e54fb3c8ec94e57bed612a");
        mockRequest.param("oldPwd", "admin");
        mockRequest.param("newPwd", "e10adc3949ba59abbe56e057f20f883e");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

}


