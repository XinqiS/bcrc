package com.sooka.common.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtil {
	
	@Value("${spring.mail.username}")
	private String fromUser;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	/**
	 * 发送邮件
	 * @param toUser 接收人邮件地址
	 * @param subject 邮件主体
	 * @param content 邮件内容
	 * @return 是否发送成功
	 */
	public boolean sendStringMail(String toUser,String subject,String content){
		boolean isSend=false;
		try{
			MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(fromUser);
	        helper.setTo(toUser);
	        helper.setSubject(subject);
	        StringBuffer sb = new StringBuffer();
	        sb.append(content);
	        //sb.append("<h1>大标题-h1</h1>").append("<p style='color:#F00'>红色字</p>").append("<p style='text-align:right'>右对齐</p>");
	        helper.setText(sb.toString(), true);
	        //发送邮件添加附件
	        //FileSystemResource fileSystemResource=new FileSystemResource(new File("D:\\20180907134954.png"));
	        //helper.addAttachment("电子发票",fileSystemResource);
	        javaMailSender.send(message);
	        isSend=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return isSend;
	}
}
