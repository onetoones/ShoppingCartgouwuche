package chenshuai.bwie.com.shoppingcart.net;

/**
 * Created by 不将就 on 2017/12/6.
 */

public interface Api {
    public static boolean ISONLINE = true;
    public static String DEV = "http://169.27.23.105";
    public static String ONLINE = "http://120.27.23.105";

    public static String HOST = ISONLINE ? ONLINE : DEV;

    public static String LOGIN = HOST + "/user/login?mobile=%s&password=%s";
    public static String REGIS = HOST + "/user/reg";
    public static String UPLOAD = HOST + "/file/upload";
    public static String CATAGORY = HOST + "/product/getCatagory";
    public static String PRODUCTCATAGORY = HOST + "/product/getProductCatagory?cid=%s";

    public static String DETAIL = HOST + "/product/getProducts?pid=%s&source=android";
    public static String PRODUCT_DETAIL = HOST + "/product/getProductDetail?pid=%s&source=android";
    //.添加购物车
    public static String ADD_CARD = HOST + "/product/addCart";
    //查询购物车
    public static String SELECT_CARD = HOST + "/product/getCarts";
        //删除购物车
    public static String DEL_CARD = HOST + "/product/deleteCart";

}
