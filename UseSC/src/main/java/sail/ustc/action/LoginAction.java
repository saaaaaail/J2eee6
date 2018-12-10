package sail.ustc.action;

import sail.ustc.model.UserBean;

public class LoginAction {
    private UserBean userBean;
    public String handleLogin(String name,String password) {
        userBean = new UserBean(name, password);
        System.out.println("执行handleLogin...");
        if (userBean.signIn(name,password)) {
            return "success";
        } else {
            return "failure";
        }
    }
}
