package com.fsw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;  

import javax.mail.Authenticator;  
import javax.mail.Message.RecipientType;  
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class SendEmail {  
	
	/**
	 * @param User_Email	邮箱地址
	 * @param biaoti	邮件标题
	 * @param user	用户名
	 * @param zhengwen	正文
	 */
	public SendEmail(String User_Email,String biaoti,String user,String zhengwen){
		 try {  
	            //创建Properties 类用于记录邮箱的一些属性  
	            final Properties props = new Properties();  
	            //表示SMTP发送邮件，必须进行身份验证  
	            props.put("mail.smtp.auth", "true");  
	            //此处填写SMTP服务器  
	            props.put("mail.smtp.host", "smtp.qq.com");  
	            //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587  
	            props.put("mail.smtp.port", "587");  
	            //此处填写你的账号  
	            props.put("mail.user", "746515766@qq.com");  
	            //此处的密码就是前面说的16位STMP口令  
	            props.put("mail.password", "soclikarpzbzbcaj");  
	            //构建授权信息，用于进行SMTP进行身份验证  
	            Authenticator authenticator = new Authenticator() {  
	  
	                protected PasswordAuthentication getPasswordAuthentication() {  
	                    // 用户名、密码  
	                    String userName = props.getProperty("mail.user");  
	                    String password = props.getProperty("mail.password");  
	                    return new PasswordAuthentication(userName, password);  
	                }  
	            };  
	            //使用环境属性和授权信息，创建邮件会话  
	            Session mailSession = Session.getInstance(props, authenticator);  
	            //创建邮件消息  
	            MimeMessage message = new MimeMessage(mailSession);  
	            //设置发件人  
	            InternetAddress form = new InternetAddress(  
	                    props.getProperty("mail.user"));  
	            message.setFrom(form);  
	  
	            //设置收件人的邮箱  
	            InternetAddress to = new InternetAddress(User_Email);  
	            message.setRecipient(RecipientType.TO, to);  
	  
	            //设置邮件标题  
	            message.setSubject(biaoti);  
	            //获取当前时间
	            String time=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
	 
	            //html文件  
	            StringBuilder sb = new StringBuilder();    
	            sb.append("<html>");  
	                sb.append("<head>");  
	                    sb.append("<title>xxx</title>");  
	                        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");   
	                        sb.append("<style type=\"text/css\">");    
	                        sb.append(".post {margin-bottom: 20px;background: #5BCE9E;}.title {padding: 5px 20px;}.posted {padding: 0 0 0 20px;font-size: small;}.story {padding: 20px;}.meta {height: 60px;padding: 40px 0 0 0;}.meta p {margin: 0;padding: 0 20px 0 0;    text-align: right;}");  
	                        sb.append("</style>");    
	                sb.append("</head>");  
	                sb.append("<body>");  
	                    sb.append("<div>");  
	                        sb.append("<div class=\"post\">");  
	                            sb.append("<h2 class=\"title\">亲爱的"+user+"</h2>");  
	                            sb.append("<h3 class=\"posted\">这是一条来自发生网的消息</h3>");  
	                            sb.append("<div class=\"story\">"+zhengwen+"</div>");  
	                            sb.append("<div class=\"meta\"><p>"+time+"</p></div>");  
	                        sb.append("</div>");  
	                    sb.append("</div>");  
	                sb.append("</body>");  
	            sb.append("</html>");  
	              
	            //设置邮件的内容体  
	            message.setContent(sb.toString(), "text/html;charset=UTF-8");  
	  
	            //最后当然就是发送邮件啦  
	            Transport.send(message);  
	            //System.out.println("发送成功");
	        } catch (AddressException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (MessagingException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }
	}

}  