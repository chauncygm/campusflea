var ioc = {
    prop : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["/conf"]
        }
    },
    dataSource : {
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            url : {java:"$prop.get('db.url')"},
            username : {java:"$prop.get('db.username')"},
            password : {java:"$prop.get('db.password')"},
            testWhileIdle : true,
            validationQuery : {java:"$prop.get('db.validationQuery')"},
            maxActive : {java:"$prop.get('db.maxActive')"}
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer : "dataSource"}]
    }
};