package cn.com.hugedata.spark.commons.service.email;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * 发送邮件服务.
 */
@Service
public class SendEmailService {

    /**
     * Log.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);
    
    /**
     * 模板文件路径.
     */
    private static final String TEMPLATE_PATH = "/templates/email";
    
    /**
     * 保存初始化的Freemaker Configuration.
     */
    private Configuration freemarkerConfiguration;
    

    @Value("${sendMail.serverHost}")
    private String sendMailserverHost;
    
    @Value("${sendMail.serverPort}")
    private int sendMailserverPort; 
    
    @Value("${sendMail.login}")
    private String sendMaillogin;
    
    @Value("${sendMail.password}")
    private String sendMailpassword; 
    
    /**
     * 初始化发送邮件服务.
     */
    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing service for send email.");
        
        // 初始化Freemarker
        freemarkerConfiguration = new Configuration(new Version("2.3.21"));
        freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), TEMPLATE_PATH);
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
    }
    
    /**
     * 发送邮件.
     * @param hostname   邮件SMTP服务器地址
     * @param port       邮件SMTP服务器端口
     * @param login      邮件服务器登录用户名
     * @param password   邮件服务器登录密码
     * @param from       发件人
     * @param to         收件人
     * @param subject    邮件主题
     * @param content    邮件内容(HTML)
     * @throws ServiceException 发送邮件失败
     */
    public void sendEmail(String hostname, int port, String login, String password, String from, String to, 
            String subject, String content) throws ServiceException {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(hostname);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(login, password));
            email.setCharset("UTF-8");
            //email.setSSLOnConnect(true);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(to);
            email.send();
            
        } catch (EmailException e) {
            LOGGER.error("Failed to send email", e);
            throw new ServiceException("发送邮件时发生错误", e);
        }
    }
    
    /**
     * 使用默认的发件人配置发送邮件.
     * @param to         收件人
     * @param subject    邮件主题
     * @param content    邮件内容(HTML)
     * @throws ServiceException 发送邮件失败
     */
    public void sendEmail(String to, String subject, String content) throws ServiceException {
        sendEmail(
                sendMailserverHost, 
                sendMailserverPort, 
                sendMaillogin, 
                sendMailpassword, 
                sendMaillogin, 
                to, 
                subject,
                content);
    }
    
    /**
     * 使用默认的发件人配置发送邮件.
     * @param to  收件人
     * @param sc  发送邮件的主题和正文
     * @throws ServiceException 发送邮件失败
     */
    public void sendEmail(String to, SendConfig sc) throws ServiceException {
        sendEmail(
                sendMailserverHost, 
                sendMailserverPort, 
                sendMaillogin, 
                sendMailpassword, 
                sendMaillogin, 
                to, 
                sc.getSubject(),
                sc.getContent());
    }
    
    /**
     * 生成并发送邮件.
     * @param to           收件人
     * @param templateName 邮件模板名称
     * @param data         用于生成邮件的数据
     * @throws ServiceException 发送邮件失败
     */
    public void sendEmail(String to, String templateName, Map<String, Object> data) throws ServiceException {
        SendConfig sc = generateEmail(templateName, data);
        sendEmail(to, sc);
    }
    
    /**
     * 生成发送邮件的主题和正文信息.
     * @param templateName  邮件模板名称
     * @param data          邮件模板所需的数据
     * @return              生成的邮件主题和正文信息
     * @throws ServiceException  生成邮件失败
     */
    public SendConfig generateEmail(String templateName, Map<String, Object> data) throws ServiceException {
        try {
            Template template = freemarkerConfiguration.getTemplate(templateName);
            template.setOutputEncoding("UTF-8");
            StringWriter w = new StringWriter();
            Environment env = template.createProcessingEnvironment(data, w);
            env.process();
            
            SendConfig sc = new SendConfig();
            sc.setSubject(env.getVariable("subject").toString());
            sc.setContent(w.toString());
            return sc;
            
        } catch (IOException e) {
            LOGGER.error("Failed to generate email.", e);
            throw new ServiceException("生成邮件失败.", e);
            
        } catch (TemplateException e) {
            LOGGER.error("Failed to generate email.", e);
            throw new ServiceException("生成邮件失败.", e);
        }
    }
    
    /**
     * 包含发送邮件的主题和正文.
     */
    public static class SendConfig {
        /**
         * 发送邮件的主题.
         */
        private String subject;
        
        /**
         * 发送邮件的正文.
         */
        private String content;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
