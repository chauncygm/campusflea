package com.smart.filter;

import com.smart.bean.Account;
import com.smart.common.Constant;
import com.smart.dao.DaoManager;
import com.smart.struct.CommonResult;
import com.smart.utils.JwtHelper;
import com.smart.utils.StringUtil;
import io.jsonwebtoken.Claims;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.view.UTF8JsonView;

import javax.servlet.http.HttpServletRequest;

@Filters
@IocBean
public class SignFilter implements ActionFilter{

    @Override
    public View match(ActionContext context) {
        UTF8JsonView view = new UTF8JsonView();
        HttpServletRequest request = context.getRequest();
        String tokenStr = request.getHeader("authorization");
        String id = request.getParameter("id");
        if (StringUtil.isBlank(tokenStr) || StringUtil.isBlank(id)) {
            view.setData(new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "param error"));
            return view;
        }
        try {
            Claims claims = JwtHelper.parseJWT(tokenStr, Constant.SECRETKEY);
            if (claims != null) {
                String authId = claims.getId();
                Account account = DaoManager.getInstance().getAccountDao().getAccount(Integer.valueOf(id));
                if (account != null && authId.equals(id) &&
                        claims.getSubject().equals(account.getUsername() + account.getPassword())) {
                    request.setAttribute("userId", account.getAccountId());
                    return null;
                }
            }
            view.setData(new CommonResult(Constant.RESCODE_TOKEN_INVALID, "token invalid"));
        } catch (Exception e) {
            view.setData(new CommonResult(Constant.RESCODE_TOKEN_INVALID, "token invalid"));
            e.printStackTrace();
            return view;
        }
        return view;
    }
}
