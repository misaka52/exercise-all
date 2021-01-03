package com.kkb.spring.reader;

import com.kkb.spring.registry.BeanDefinitionRegistry;
import com.kkb.spring.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * 专门针对XML进行读取的BeanDefinition阅读器
 * 
 * @author 灭霸詹
 *
 */
public class XmlBeanDefinitionReader {

	private BeanDefinitionRegistry beanDefinitionRegistry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	public void loadBeanDefinitions(InputStream inputStream) {
		Document document = DocumentReader.createDocument(inputStream);
		XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
		documentReader.registerBeanDefinitions(document.getRootElement());
	}

}
