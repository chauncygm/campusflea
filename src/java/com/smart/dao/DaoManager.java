package com.smart.dao;

import com.smart.bean.Message;

public class DaoManager {

    /**
     * 枚举实现单例
     */
    private DaoManager(){}
    private enum Singleton{
        INSTANCE;
        DaoManager processor = new DaoManager();
        DaoManager getProcessor () {
            return processor;
        }
    }
    public static DaoManager getInstance() {
        return Singleton.INSTANCE.getProcessor();
    }

    private AccountDao accountDao;

    private MessageDao messageDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
}
