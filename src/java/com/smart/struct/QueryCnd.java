package com.smart.struct;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;

import java.util.Date;

/**
 * goods query cnd
 */
public class QueryCnd {

    private int userId;

    private String goodsName;

    private Integer categoryId;

    private Integer campusId;

    private Integer areaId;

    private Integer minPrice;

    private Integer maxPrice;

    private Integer queryType;

    public Condition getCnd() {
        long now = new Date().getTime();
        Condition cnd = null;
        switch (queryType) {
            case QueryType.USER :
                cnd = Cnd.where("user_id", "=", userId);
                break;
            case QueryType.NEW :
                cnd = Cnd.where("is_sell", "=", 0).and("deadline", ">", now)
                            .orderBy("put_time", "DESC");
                break;
            case QueryType.HOT :
                cnd = Cnd.where("is_sell", "=", 0).and("deadline", ">", now)
                            .orderBy("love_num", "DESC").orderBy("put_time", "DESC");
                break;
            default:
                break;
        }
        return cnd;
    }

    public Sql getSql(){
        Sql sql = Sqls.create(getSqlStr());
        setParam(sql);
        sql.setCallback(Sqls.callback.entities());
        return sql;
    }

    private String getSqlStr() {
        String sqlStr = "select * from t_goods where is_sell = 0 and deadline > unix_timestamp(now()) ";
        if (goodsName != null) {
            sqlStr += "and goods_name like @goodsName ";
        }
        if (categoryId != null) {
            sqlStr += "and category_id = @categoryId ";
        }
        if (campusId != null) {
            sqlStr += "and user_id in (select user_id from t_user where campus_id = @campusId) ";
        } else {
            if (areaId != null) {
                sqlStr += "and user_id in (select user_id from t_user where campus_id in "
                        + "(select campus_id from t_campus where area_id = @areaId)) ";
            }
        }
        if (minPrice != null) {
            sqlStr += "and real_price > @minPirce";
        }
        if (maxPrice != null) {
            sqlStr += "and real_price < @maxPirce";
        }
        return sqlStr;
    }

    public void setParam(Sql sql) {
        if (goodsName != null) {
            sql.setParam("goodsName", goodsName);
        }
        if (categoryId != null) {
            sql.setParam("categoryId", categoryId);
        }
        if (campusId != null) {
            sql.setParam("campusId", campusId);
        } else {
            if (areaId != null) {
                sql.setParam("areaId", areaId);
            }
        }
        if (minPrice != null) {
            sql.setParam("minPrice", minPrice);
        }
        if (maxPrice != null) {
            sql.setParam("maxPrice", maxPrice);
        }
    }

    public Integer getQueryType() {
        return queryType;
    }

    @Override
    public String toString() {
        return "QueryCnd{" +
                "userId=" + userId +
                ", goodsName='" + goodsName + '\'' +
                ", categoryId=" + categoryId +
                ", campusId=" + campusId +
                ", areaId=" + areaId +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", queryType=" + queryType +
                '}';
    }
}
