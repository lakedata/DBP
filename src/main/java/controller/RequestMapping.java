package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.policy.*;
import controller.scrap.*;
import controller.post.*;
import controller.reply.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	
    	logger.debug("RequestMaaping");
    	mappings.put("/", null);
    	
    	// 占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
        mappings.put("/", new ForwardController("index.jsp"));
    	
    	
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        
       
        
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update", new UpdateUserController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
        //policy
        mappings.put("/policy/insert", new InsertPolicyController());
        mappings.put("/policy/list", new ListPolicyController());
        mappings.put("/policy/view", new ViewPolicyController());
        mappings.put("/policy/search", new SearchPolicyController());
        mappings.put("/policy/delete", new DeletePolicyController());
//        mappings.put("/policy/update/form", new UpdatePolicyController());
        mappings.put("/policy/update", new UpdatePolicyController());
       
        //scrap
        mappings.put("/policy/scrap/add", new AddScrapController());
        mappings.put("/policy/scrap/cancel", new CancelScrapController());
       // mappings.put("/post/scrap/list", new ListScrapController());
        
        //comment

//		mappings.put("/post/reply/add", new CreateReplyController());
        
        //post
        mappings.put("/post/write", new ForwardController("/post/postWrite.jsp"));
        
        mappings.put("/post/add", new AddPostController());
        mappings.put("/post/list", new ListPostController());
        mappings.put("/post/view", new ViewPostController()); //占싱몌옙 占쏙옙占쏙옙 占십울옙
        mappings.put("/post/delete", new DeletePostController());
        mappings.put("/post/update", new UpdatePostController());
        
        //mypage
        mappings.put("/mypage", new ForwardController("/user/mypage.jsp")); 
        mappings.put("/mypage/scrap/view", new ListScrapController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/mypage/calendar/view", new ForwardController("/user/calendar.jsp"));//캘占쏙옙占쏙옙.jsp 占쏙옙占쏙옙 占십울옙
        
        
        //占쏙옙占쏙옙 占쏙옙 占쌜뤄옙 占쏙옙크占쏙옙占쏙옙
        mappings.put("/mypage/myPost", new UserPostListController());
        //占쏙옙占쏙옙 占쏙옙 占쏙옙紡占� 占쏙옙크占쏙옙占쏙옙
        mappings.put("/mypage/myComment", new ForwardController("/user/myComment.jsp"));
        
        //main page
        mappings.put("/main", new ForwardController("/home/main.jsp"));
        
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {
    	logger.debug("mappings.get" +mappings.get(uri));
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}
