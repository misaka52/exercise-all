package com.ysc.ssm.service;

import java.util.List;

import com.ysc.ssm.mapper.ItemMapper;
import com.ysc.ssm.po.Item;
import com.ysc.ssm.po.ItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;
	
	@Override
	public List<Item> queryItemList() {
		ItemExample example = new ItemExample();
		List<Item> list = mapper.selectByExample(example );
		return list;
	}

}
