package com.smart.module;

import com.smart.bean.Category;
import com.smart.bean.Comments;
import com.smart.bean.Goods;
import com.smart.bean.User;
import com.smart.common.Constant;
import com.smart.dao.CategoryDao;
import com.smart.dao.CommentDao;
import com.smart.dao.GoodsDao;
import com.smart.dao.UserDao;
import com.smart.filter.AuthFilter;
import com.smart.struct.CommonResult;
import com.smart.struct.QueryCnd;
import com.smart.struct.QueryType;
import org.apache.log4j.Logger;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;
import java.util.*;

@At("goods")
@IocBean
public class GoodsModule {

    private static final Logger logger = Logger.getLogger(GoodsModule.class);

    @Inject
    private GoodsDao goodsDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CommentDao commentDao;

    @Inject
    private CategoryDao categoryDao;

    @At
    public Object getCategory() {

        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    /**
     * put goods info
     * not complete
     */
    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    @AdaptBy(type= UploadAdaptor.class, args={"${app.root}/upload/goods"})
    public Object addGoods(@Param("..")Goods goods, @Param("picture")TempFile[] pictrues) {
        CommonResult result = null;
        for (TempFile tf : pictrues) {
            System.out.println("name:" + tf.getName());
            System.out.println("file name:"+tf.getSubmittedFileName());
            System.out.println("file size:" + tf.getSize());
            //
            //
        }
        goodsDao.insert(goods);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "add goods succeed");
    }


    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object goodsList(@Param("..") QueryCnd cnd, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        try {
            if (cnd.getQueryType() == QueryType.CONDITION) {
                goodsList = goodsDao.getGoodsList(cnd.getSql());
            } else {
                goodsList = goodsDao.getGoodsList(cnd.getCnd());
            }
            for (Goods goods : goodsList) {
                users.add(userDao.get(goods.getUserId()));
            }
            data.put("users", users);
            data.put("goodsList", goodsList);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "get goods list failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object goodsDetail(@Param("goodsId") int goodsId) {
        try {
            Goods goods = goodsDao.get(goodsId);
            goods.setViewNum(goods.getViewNum() + 1);
            goodsDao.update(goods);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", goods);
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "get good info failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object hitLover(@Param("goodsId") int goodsId, @Param("add") boolean add, HttpServletRequest request) {
        try {
            //add or minus goods lover number
            Goods goods = goodsDao.get(goodsId);
            int addNum = add ? 1 : -1;
            goods.setLoveNum(goods.getLoveNum() + addNum);
            //add or minus goods putter lover num
            User putter = userDao.get(goods.getUserId());
            putter.setLikes(putter.getLikes() + addNum);
            //add or remove goodsId from user lover num and lover list
            User user = userDao.get((int) request.getAttribute("userId"));
            if (add) {
                if (!user.getLikeList().contains("," + goods.getGoodId())) {
                    user.setLikeList(user.getLikeList().concat("," + goods.getGoodId()));
                }
            } else {
                if (user.getLikeList().contains("," + goods.getGoodId())) {
                    user.setLikeList(user.getLikeList().replace("," + goods.getGoodId(), ""));
                }
            }
            //update
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    userDao.update(user);
                    userDao.update(putter);
                    goodsDao.update(goods);
                }
            });
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "hit love failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object hitCollection(@Param("goodsId") int goodsId, @Param("add") boolean add, HttpServletRequest request) {
        try {
            //add or minus goods collection number
            Goods goods = goodsDao.get(goodsId);
            int addNum = add ? 1 : -1;
            goods.setCollectNum(goods.getCollectNum() + addNum);
            //add or minus goods putter collection num
            User putter = userDao.get(goods.getUserId());
            putter.setCollections(putter.getCollections() + addNum);
            //add or remove goodsId from user collection num and collection list
            User user = userDao.get((int) request.getAttribute("userId"));
            if (add) {
                if (!user.getCollectionList().contains("," + goods.getGoodId())) {
                    user.setCollectionList(user.getCollectionList().concat("," + goods.getGoodId()));
                }
            } else {
                if (user.getCollectionList().contains("," + goods.getGoodId())) {
                    user.setCollectionList(user.getCollectionList().replace("," + goods.getGoodId(), ""));
                }
            }
            //update
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    userDao.update(user);
                    userDao.update(putter);
                    goodsDao.update(goods);
                }
            });
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "hit collection failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object addComment(@Param("..") Comments comments) {
        try {
            comments.setTime(new Date().getTime());
            commentDao.insert(comments);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object listComment(@Param("goodsId") int goodsId) {
        List<Comments> comments = commentDao.getComment(goodsId);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", comments);
    }

}

