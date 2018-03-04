package com.smart.Manager;

public class CategoryManager {

    private CategoryManager() {}

    private enum Singleton {
        INSTANCE;
        CategoryManager manager;

        Singleton() {
            this.manager = new CategoryManager();
        }
        CategoryManager getProcessor() {
            return manager;
        }
    }

    public CategoryManager getInstance() {
        return Singleton.INSTANCE.getProcessor();
    }


}
