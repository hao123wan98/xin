import com.xin.user.controller.UserController;
import com.xin.webservice.EmailClient;
import com.xin.webservice.EmailSendAccountVO;
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
public class EmailTest extends BaseTestController {
//    @Autowired
//    EmailSendAccountVO accountVO;

    @Test
    public void Test() {
    }

    //    @Test
    public void sendEmail() throws Exception {
        EmailSendAccountVO vo = new EmailSendAccountVO();
        vo.setEmail("guoyong.shi@zhenhr.com");
        vo.setPwd("Sgy9917");
        vo.setSmtp("smtp.exmail.qq.com");

//        vo.setEmail("shiguoyong@aliyun.com");
//        vo.setPwd("sgy9917");
//        vo.setSmtp("smtp.mxhichina.com");

        vo.setShowName("馨招聘");

        EmailClient client = new EmailClient();
        client.sendMail(vo, "测试邮件", "this is a test", "guoyong.shi@zhenhr.com");

    }



}


