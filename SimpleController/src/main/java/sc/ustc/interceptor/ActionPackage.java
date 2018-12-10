package sc.ustc.interceptor;

import sc.ustc.util.XmlUtil;

import java.lang.reflect.Method;

public class ActionPackage implements Action{

    @Override
    public String doAction(String actionName,String username,String userpass) {
        try {
            String fileString = this.getClass().getClassLoader().getResource("controller.xml").getPath();
            XmlUtil xmlUtil = XmlUtil.getInstance();
            String actionMess = xmlUtil.parseXml(fileString,"action",actionName);
            String[] mess = actionMess.split(",");
            //System.out.println("doAction: "+actionMess);
            //System.out.println("doAction: mess[0]:"+mess[0]);
            if(mess[0].equals("action:failure")){
                return mess[0];
            }
            Class actionClass = Class.forName(mess[0]);
            Method method = actionClass.getMethod(mess[1],String.class,String.class);
            Object obj = method.invoke(actionClass.newInstance(),username,userpass);
            //System.out.println((String)obj);
            String result = xmlUtil.parseChild(fileString,actionName,(String)obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
