package com.example.admin.a1508bnsg.net;

/**
 * Created by peng on 2017/9/27.
 */

public interface Api {
    public static final String devIp = "http://169.254.194.60";
    //public static final String devIp = "http://192.168.137.247";
    public static final String FEILEI = devIp + "/mobile/index.php?act=goods_class";
    public static final String REGISTER = devIp + "/mobile/index.php?act=login&op=register";
    public static final String LOGIN = devIp + "/mobile/index.php?act=login";
    public static final String CLASS = devIp + "/mobile/index.php?act=goods_class";
    public static final String RIGHT = devIp + "/mobile/index.php?act=goods_class&gc_id=%s";
    public static final String GOODS_LIST = devIp + "/mobile/index.php?act=goods&op=goods_list&page=100";
    public static final String GOODS_DETAILS = devIp + "/mobile/index.php?act=goods&op=goods_detail&goods_id=%s";
    public static final String ADD_CARD = devIp + "/mobile/index.php?act=member_cart&op=cart_add";
    public static final String CARD = devIp + "/mobile/index.php?act=member_cart&op=cart_list";

}
