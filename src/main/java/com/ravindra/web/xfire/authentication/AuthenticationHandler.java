package com.ravindra.web.xfire.authentication;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;

public class AuthenticationHandler extends AbstractHandler {

    public void invoke(MessageContext messageContext) throws Exception {
        if (messageContext.getInMessage().getHeader() == null) {
            throw new XFireFault("请求必须包含验证信息", XFireFault.SENDER);
        }
        Element token = messageContext.getInMessage().getHeader().getChild("AuthenticationToken");
        if (token == null) {
            throw new XFireFault("请求必须包含身份验证信息", XFireFault.SENDER);
        }
        String username = token.getChild("Username").getValue();
        String password = token.getChild("Password").getValue();
        try {
            // 进行身份验证
            if (username.equals("abcd") && password.equals("1234"))
                // 这语句不显示
                System.out.println("身份验证通过");
            else
                throw new Exception();
        } catch (Exception e) {
            throw new org.codehaus.xfire.fault.XFireFault("非法的用户名和密码",
                    org.codehaus.xfire.fault.XFireFault.SENDER);
        }
    }
}
