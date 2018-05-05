package com.smart.module;

import com.smart.bean.Message;
import com.smart.common.Constant;
import com.smart.dao.MessageDao;
import com.smart.dao.UserDao;
import com.smart.dto.MessageInfo;
import com.smart.filter.AuthFilter;
import com.smart.struct.CommonResult;
import com.smart.utils.FileUtil;
import org.apache.log4j.Logger;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@At("/chat")
@IocBean
public class ChatModule {

    private static final Logger logger = Logger.getLogger(ChatModule.class);

    @Inject
    private MessageDao messageDao;

    @Inject
    private UserDao userDao;

    @At
    @Ok("json")
    @Filters(@By(type=AuthFilter.class))
    public Object getMsgList(@Param("id") int id) {
        List<MessageInfo> msgInfo = null;
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type=AuthFilter.class))
    public Object history(@Param("id") int id, @Param("oid") int oid, Pager pager) {
        if (!userDao.isExist(oid)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "oid not exist");
        }
        try {
            List<Message> messages = messageDao.getHistory(id, oid, pager);
            pager.setRecordCount(messageDao.count(id, oid));
            QueryResult result = new QueryResult(messages, pager);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "get history message succeed", result);
        } catch (Exception e) {
            logger.error(String.format("get history message faild : id[%s] oid[%s]", id, oid));
        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "unknow error");
    }

    @At
    @Ok("json")
    @AdaptBy(type=UploadAdaptor.class)
    public Object uploadImg(@Param("picture")TempFile picture, HttpServletRequest request) {
        String relativePath = "/upload/msgImg/";
        String path = request.getServletContext().getRealPath(relativePath);
        String uuid = UUID.randomUUID().toString();
        String picName = "";
        try {
            String suffix = picture.getFile().getName().split("\\.")[1];
            picName = uuid +"." + suffix;
            File img = new File(path + picName);
            img.createNewFile();
            FileUtil.copyFile(picture.getFile(), img);
        } catch (Exception e) {
            logger.info("图片消息上传失败");
            e.printStackTrace();
            return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "failed");
        }
        return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "ok", relativePath + picName);
    }
}
