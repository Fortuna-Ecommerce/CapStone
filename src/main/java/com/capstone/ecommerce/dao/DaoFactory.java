package com.capstone.ecommerce.dao;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Admin adminDao;
    private static Categories CategoriesDao;
    private static Products ProductsDao;

    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Admin getAdImagesDao(){
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