package com.miracle.interceptor;

import com.miracle.util.ConfigUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by yangchao2014 on 2016/12/2.
 */
public class WeChatTokenInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    return StringUtils.equalsIgnoreCase(signature, getSignature(timestamp, nonce));
  }

  /**
   * 验证签名
   *
   * @param timestamp
   * @param nonce
   */
  private static String getSignature(String timestamp, String nonce) {
    String token = ConfigUtil.getConfig("wechat.token");
    String[] arr = new String[] {token, timestamp, nonce};
    // 将token、timestamp、nonce三个参数进行字典序排序
    sort(arr);
    StringBuilder content = new StringBuilder();
    for (String anArr : arr) {
      content.append(anArr);
    }
    MessageDigest md;
    String signature = null;

    try {
      md = MessageDigest.getInstance("SHA-1");
      // 将三个参数字符串拼接成一个字符串进行sha1加密
      byte[] digest = md.digest(content.toString().getBytes());
      signature = byteToStr(digest);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return signature;
  }

  /**
   * 将字节数组转换为十六进制字符串
   *
   * @param byteArray
   * @return
   */
  private static String byteToStr(byte[] byteArray) {
    String strDigest = "";
    for (byte aByteArray : byteArray) {
      strDigest += byteToHexStr(aByteArray);
    }
    return strDigest;
  }

  /**
   * 将字节转换为十六进制字符串
   *
   * @param mByte
   * @return
   */
  private static String byteToHexStr(byte mByte) {
    char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    char[] tempArr = new char[2];
    tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
    tempArr[1] = Digit[mByte & 0X0F];

    return new String(tempArr);
  }

  private static void sort(String a[]) {
    for (int i = 0; i < a.length - 1; i++) {
      for (int j = i + 1; j < a.length; j++) {
        if (a[j].compareTo(a[i]) < 0) {
          String temp = a[i];
          a[i] = a[j];
          a[j] = temp;
        }
      }
    }
  }
}
