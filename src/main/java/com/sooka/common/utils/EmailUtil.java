package com.sooka.common.utils;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class EmailUtil {
	@Value("${spring.mail.username}")
	private  String from ; // 发件人邮箱地址
	@Value("${spring.mail.username}")
	private  String user ; // 发件人称号，同邮箱地址
	@Value("${spring.mail.password}")
	private  String password ; // 发件人邮箱客户端授权码
	@Value("${spring.mail.host}")
	private  String host;

	/**
	 * 发送邮件
	 * 
	 * @param to  发送邮箱号码
	 * @param text 发送的内容
	 * @param title 发送时的标题
	 * @return true  成功 or false 失败
	 */
	public  boolean sendMail(String to, String text, String title) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", host); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true"); // 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
																// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true); // 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session); // 加载发件人地址
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to)); // 加载收件人地址
			message.setSubject(title); // 加载标题
			Multipart multipart = new MimeMultipart(); // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			BodyPart contentPart = new MimeBodyPart(); // 设置邮件的文本内容
			contentPart.setContent(text, "text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);
			message.setContent(multipart);
			message.saveChanges(); // 保存变化
			Transport transport = session.getTransport("smtp"); // 连接服务器的邮箱
			transport.connect(host, user, password); // 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
