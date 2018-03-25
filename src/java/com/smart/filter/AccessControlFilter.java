package com.smart.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

import javax.servlet.http.HttpServletResponse;

public class AccessControlFilter implements ActionFilter {

    @Override
    public View match(ActionContext context) {
        HttpServletResponse response = context.getResponse();
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        return null;
    }
}
