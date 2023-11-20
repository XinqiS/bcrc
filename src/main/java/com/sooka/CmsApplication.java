package com.sooka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;



@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class CmsApplication {

	public static void main(String[] args){
		SpringApplication.run(CmsApplication.class, args);
	}


	/*public static void main(String[] args) {
		int total = 502; //总条数
		int pageNumber=1//初始页码
		int pageSise = 5; //每页显示条数
		int totalPage = (total  +  pageSise  - 1) / pageSise;//总页数
		int start=(3-1)*pageSise;
		pageNumber+pageSise



	}*/
	/**
	 * 文件上传配置
	 *
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//  单个数据大小
		factory.setMaxFileSize("5242880KB"); // KB,MB
		/// 总上传数据大小
		factory.setMaxRequestSize("10240000KB");
		return factory.createMultipartConfig();
	}
}