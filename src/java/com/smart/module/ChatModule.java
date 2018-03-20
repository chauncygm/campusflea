package com.smart.module;

import com.smart.bean.Message;
import com.smart.common.Constant;
import com.smart.dao.MessageDao;
import com.smart.dao.UserDao;
import com.smart.filter.AuthFilter;
import com.smart.struct.CommonResult;
import org.apache.log4j.Logger;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import java.util.List;

@At("/chat")
@IocBean
public class ChatModule {

    private static final Logger logger = Logger.getLogger(ChatModule.class);

    @Inject
    private MessageDao messageDao;

    @Inject
    private UserDao userDao;

    /**
     * query history message
     */
    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object history(@Param("id") int id, @Param("oid") int oid, Pager pager) {
        if (!userDao.isExist(oid)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "oid not exist");
        }
        try {
            List<Message> messages = messageDao.getHistory(id, oid, pager);
            pager.setRecordCount(messageDao.count(id, oid));
            QueryResult result = new QueryResult(messages, pager);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "get history message succeed");
        } catch (Exception e) {
            logger.error("get history message faild:id[" + id + "] oid[" + oid + "]");
        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "unknow error");
    }

}
