package com.smart.filter;

import com.smart.common.Constant;
import com.smart.struct.CommonResult;
import com.smart.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.view.HttpStatusView;
import org.nutz.mvc.view.UTF8JsonView;

import javax.servlet.http.HttpServletRequest;

@Filters
public class SignFilter implements ActionFilter{

    @Override
    public View match(ActionContext context) {
        UTF8JsonView view = null;
        HttpServletRequest httpRequest = context.getRequest();
        String auth = httpRequest.getHeader("Authorization");
        Claims claims = JwtHelper.parseJWT(auth, Constant.SECRETKEY);
        if (claims == null) {
            view.setData(new CommonResult(Constant.RESCODE_TOKEN_INVALID, "token invalid"));
            return view;
        }
        return new HttpStatusView(400);
    }
}
