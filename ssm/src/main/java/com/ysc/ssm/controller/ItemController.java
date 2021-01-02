package com.ysc.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.ssm.service.ItemService;
import com.ysc.ssm.po.Item;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;

	@RequestMapping("queryItem")
	@ResponseBody
	public List<Item> queryItem(){
		return service.queryItemList();
	}
}
