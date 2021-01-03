package com.kkb.spring.resource;

import java.io.InputStream;

/**
 * 封装XML路径信息的资源对象
 * 
 * @author 灭霸詹
 *
 */
public interface Resource {

	InputStream getResource();
}
