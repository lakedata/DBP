package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.policy.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	//policy uri
    	mappings.put("/", null);
    	
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        
        // ȸ�� ���� �� ��û�� ���� ��û ó�� ���� (���� Ŀ�´�Ƽ ���� �޴� �߰��� ����)
//      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());

        // ����� ���� ���� �� ��û�� ���� ��û ó�� ����
//      mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController());
        
        
        //policy
        mappings.put("/policy/upload/form", new ForwardController("/policy/uploadForm.jsp")); //정책 등록
        mappings.put("/policy/list", new ListPolicyController()); 
        mappings.put("/policy/view", new ViewPolicyController());
        mappings.put("/policy/search", new SearchPolicyController());
        mappings.put("/policy/insert", new InsertPolicyController());
        mappings.put("/policy/delete", new DeletePolicyController());
        mappings.put("/policy/update", new UpdatePolicyController());
        
        //post
//        mappings.put("/post/upload", new UploadPostController());
//        mappings.put("/post/list", new ListPostController());
//        mappings.put("/post/view", new ViewPostController());
//        mappings.put("/post/delete", new DeletePostController());
        
        //comment
        mappings.put("/post/comment/upload", new UpdatePolicyController());
        mappings.put("/post/comment/agree", new UpdatePolicyController());
        mappings.put("/post/comment/disagree", new UpdatePolicyController());
        
        //mypage는 컨트롤러를 만들어야 하는 애인가? 
        mappings.put("/mypage/myInfo", new UpdatePolicyController());
        mappings.put("/mypage/myInfo/update", new UpdatePolicyController());
        mappings.put("/mypage/scrap/view", new UpdatePolicyController());
        mappings.put("/mypage/scrap/delete", new UpdatePolicyController());
        mappings.put("/mypage/post/view", new UpdatePolicyController());
        mappings.put("/mypage/post/delete", new UpdatePolicyController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
