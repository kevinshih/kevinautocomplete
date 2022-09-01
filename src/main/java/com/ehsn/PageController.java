package com.ehsn;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String login() {
	    logger.info("** 請輸入搜尋關鍵字");
        return "index"; // index.jsp
	}

	@GetMapping("/index")
	public String index() {
		logger.info("** 請輸入搜尋關鍵字");
		return "index"; // index.jsp
	}
	
}