package cn.no7player.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.no7player.model.ResultDataModel;
import cn.no7player.model.User;
import cn.no7player.service.UserService;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUsers")
	@ResponseBody
	public ResultDataModel<User> getDepartment(
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
			@RequestParam(value = "sortName", required = false, defaultValue = "age") String sortName) {
		ResultDataModel<User> resultDataModel = new ResultDataModel<>();
		User user = new User();
		user.setLimit(limit);
		user.setOffset(offset);
		if (!StringUtils.isEmpty(name)) {
			user.setName(name);
		}
		List<User> rows = userService.getUsers(user);

		Comparator<User> comparator = (h1, h2) -> h1.getAge().compareTo(h2.getAge());
		if(sortOrder.equalsIgnoreCase("asc")){
			rows.sort(comparator);
		}else{
			rows.sort(comparator.reversed());
		}
		
		
		resultDataModel.setRows(rows);
		resultDataModel.setTotal(userService.getUserCount(user));
		
		return resultDataModel;
	}

}
