package com.rdxer.jwtsso.security;


public class JWTConfiguration {

    public static String jwt_prefix = "Bearer";
    public static String header_auth_key = "Authorization";
    public static String header_refresh_key = "RefreshToken";

    public static String secret = "nRvyYC4soFxBdZ-F-5Nnzz5USXstR1YylsTd-mA0aKtI9HUlriGrtkf-TiuDapkLiUCogO3JOK7kwZisrHp6wA";

    /// 超时时长 1 天
    public static long sessionTime =  1000 * 60 * 60 * 24;

    /// 超时时长 30 天
    public static long sessionTime_remember_me =  1000L * 60 * 60 * 24 * 30;


}
