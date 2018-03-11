package com.smart.filter;

import org.nutz.mvc.ActionChain;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.Filters;

import javax.servlet.http.HttpServletRequest;

@Filters
public class SignFilter implements ActionFilter{

    @Override
    public View match(ActionContext context) {
        HttpServletRequest request = context.getRequest();
        String tokenStr = request.getParameter("token");

        return null;
    }
}
