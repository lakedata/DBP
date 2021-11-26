package controller.scrap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Scrap;
import model.service.ScrapManager;

public class GetScrapController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ScrapManager scMan = ScrapManager.getInstance();
		List<Scrap> scrapList = scMan.getScrapList(request.getParameter("userId"));
		
		request.setAttribute("scrapList", scrapList);
		return "/scrap/scrapView";
	}
	

}
