import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Created by guoyongshi on 17/5/25.
 */

//@WebMvcTest(controllers = UserController.class)
public class EspCompanyTest extends BaseTestController {

    @Test
    public void Test() {

    }

    //    @Test
    public void list() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/mcompany/list");
        mockRequest.header("token", this.token);

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void setCompany() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/mcompany/set");
        mockRequest.header("token", this.token);
        mockRequest.param("name", "测试企业1");
        mockRequest.param("email", "test@qq.com");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void linkUser() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/mcompany/linkuser");
        mockRequest.header("token", this.token);
        mockRequest.param("companyId", "4");
        mockRequest.param("mobile", "13699274930");
        mockRequest.param("pwd", "123456");


        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

}


