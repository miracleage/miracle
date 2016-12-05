package com.miracle.service;


import com.miracle.util.LoggerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by yangchao2014 on 2016/12/2.
 */
@Service
public class EmailTaskService {
  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private SimpleMailMessage simpleMailMessage;

  @Autowired
  private TaskExecutor taskExecutor;

  /* 异步发送邮件 */
  public void asynSend(String to, String subject, String text) {
    this.taskExecutor.execute(new SendMailThread(to, subject, text));
  }

  /* 异步发送邮件 */
  public void asynSend(List<String> tos, String subject, String text) {
    this.taskExecutor.execute(new SendMailsThread(tos, subject, text));
  }

  /**
   * 发送邮件
   *
   * @param to 收件人邮箱
   * @param subject 邮件主题
   * @param content 邮件内容
   */
  public void sendMail(String to, String subject, String content) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
      messageHelper.setFrom(simpleMailMessage.getFrom());
      if (subject != null) {
        messageHelper.setSubject(subject);
      } else {
        messageHelper.setSubject(simpleMailMessage.getSubject());
      }
      messageHelper.setTo(to);
      messageHelper.setText(content, true);
      mailSender.send(message);
    } catch (MessagingException e) {
      LoggerUtil.error("发送邮件失败[" + to + "][" + subject + "][" + content + "]", e);
    }
  }

  // 内部线程类，利用线程池异步发邮件。
  private class SendMailThread implements Runnable {
    private String to;
    private String subject;
    private String content;

    private SendMailThread(String to, String subject, String content) {
      super();
      this.to = to;
      this.subject = subject;
      this.content = content;
    }

    @Override
    public void run() {
      sendMail(to, subject, content);
    }
  }

  // 内部线程类，利用线程池异步发邮件。
  private class SendMailsThread implements Runnable {
    private List<String> tos;
    private String subject;
    private String content;

    private SendMailsThread(List<String> tos, String subject, String content) {
      super();
      this.tos = tos;
      this.subject = subject;
      this.content = content;
    }

    @Override
    public void run() {
      for (String to : tos) {
        sendMail(to, subject, content);
      }
    }
  }
}
