package com.ravindra.web.xfire.authentication;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;

public class AuthenticationHandler extends AbstractHandler {

    public void invoke(MessageContext messageContext) throws Exception {
        if (messageContext.getInMessage().getHeader() == null) {
            throw new XFireFault("������������֤��Ϣ", XFireFault.SENDER);
        }
        Element token = messageContext.getInMessage().getHeader().getChild("AuthenticationToken");
        if (token == null) {
            throw new XFireFault("���������������֤��Ϣ", XFireFault.SENDER);
        }
        String username = token.getChild("Username").getValue();
        String password = token.getChild("Password").getValue();
        try {
            // ���������֤
            if (username.equals("abcd") && password.equals("1234"))
                // ����䲻��ʾ
                System.out.println("�����֤ͨ��");
            else
                throw new Exception();
        } catch (Exception e) {
            throw new org.codehaus.xfire.fault.XFireFault("�Ƿ����û���������",
                    org.codehaus.xfire.fault.XFireFault.SENDER);
        }
    }
}
