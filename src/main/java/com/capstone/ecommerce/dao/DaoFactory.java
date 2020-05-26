package com.capstone.ecommerce.dao;

public class DaoFactory {
    private static Products ProductsDao;
    private static Users usersDao;
    private static Admin adminDao;
    private static Categories CategoriesDao;


    private static Config config = new Config();

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Admin getAdminDao(){
        if (adminDao == null) {
            adminDao = new MySQLAdminDao(config);
        }
        return adminDao;
    }

    public static Categories getCategoriesDao(){
        if (CategoriesDao == null) {
            CategoriesDao = new MySQLCategoriesDao(config);
        }
        return CategoriesDao;
    }
}