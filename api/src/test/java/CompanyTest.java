import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Created by guoyongshi on 17/5/25.
 */

//@WebMvcTest(controllers = UserController.class)
public class CompanyTest extends BaseTestController {
    @Test
    public void Test() {

    }

    //    @Test
    public void getInfo() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/company/info/get");
        mockRequest.header("token", this.token);

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void infoSet() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/company/info/set");
        mockRequest.header("token", this.token);

        mockRequest.param("name", "测试企业");
        mockRequest.param("contact", "联系人");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void login() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/login");
        mockRequest.param("mobile", "13699274927");
        mockRequest.param("pwd", "123456");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void setInfo() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/user/set");
        mockRequest.header("token", this.token);
        mockRequest.param("gender", "1");
        mockRequest.param("name", "sgy");

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    //    @Test
    public void postionList() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/company/postion/list");
        mockRequest.header("token", this.token);

        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

//    @Test
    public void postionSet() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/company/postion/set");
        mockRequest.header("token", this.token);

        mockRequest.param("name", "技术总监");
        mockRequest.param("workPlace", "北京");
        mockRequest.param("salary", "15k-30k");
        mockRequest.param("introduce", "企业简介");


        MvcResult result = mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }
}


