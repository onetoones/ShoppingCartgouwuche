package chenshuai.bwie.com.shoppingcart.presenter;


import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chenshuai.bwie.com.shoppingcart.bean.LoginBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.ILoginrModel;
import chenshuai.bwie.com.shoppingcart.model.LoginModel;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import chenshuai.bwie.com.shoppingcart.view.IView.IFragmentmy;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class LoginPersenter {
    private IFragmentmy fragmentmy;
    private final ILoginrModel iLoginrModel;

    public LoginPersenter(IFragmentmy fragmentmy) {
        this.fragmentmy = fragmentmy;
        iLoginrModel = new LoginModel();
    }

    public void regis() {
        String name = fragmentmy.getName();
        String pwd = fragmentmy.getPwd();
        if (checkAccount(name) && checkPwd(pwd)) {
           iLoginrModel.getlogin(name, pwd, new OnNetListener<LoginBean>() {
               @Override
               public void onSuccess(LoginBean loginBean) {
                   fragmentmy.tz(loginBean);
               }

               @Override
               public void onFailure(Exception e) {

               }
           });

        }
    }

    private boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            //给用户提示，输入的账号不能为空
            fragmentmy.show("请输入密码");
            return false;
        }


        if (pwd.length() != 6) {
            fragmentmy.show("请输入6位密码");
            return false;
        }
        return true;
    }


    /**
     * 验证手机号是否正确
     *
     * @param account
     */
    private boolean checkAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            //给用户提示，输入的账号不能为空
            fragmentmy.show("请输入账号");
            return false;
        }
        if (!isMobileNO(account)) {
            fragmentmy.show("请输入正确的手机号");
            return false;
        }
        return true;
    }


    /*
    判断是否是手机号
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
