package com.nexiilabs.emplyoeeproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class EmplyoeeconsumerController {
	@Autowired
	ConsumerService consumerService;
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	public String getUser() {
		return consumerService.getUser();
	}

}
