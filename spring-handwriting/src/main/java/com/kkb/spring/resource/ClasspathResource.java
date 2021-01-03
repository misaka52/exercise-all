package com.kkb.spring.resource;

import java.io.InputStream;

/**
 * 用来封装存储到classpath路径下的xml资源信息
 * 
 * @author 灭霸詹
 *
 */
public class ClasspathResource implements Resource {
	private String location;

	public ClasspathResource(String location) {
		this.location = location;
	}

	@Override
	public InputStream getResource() {
		return this.getClass().getClassLoader().getResourceAsStream(location);
	}

}
