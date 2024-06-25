package com.carlitos.Pronacej.Utils;

public class Apis {

    public static final String URL_001 = "http://181.176.172.117:8081";
    //public static final String URL_001 = "http://192.168.0.101:8080";

    /// CENTRO JUVENIL CJDR
    public static CjdrService getCjdrService(){
        return Client.getClient(URL_001).create(CjdrService.class);
    }

    // CENTRO JUVENIL SOA
    public static SoaService getSoaService(){
        return Client.getClient(URL_001).create(SoaService.class);
    }

    public static LoginService getApiService(){
        return Client.getClient(URL_001).create(LoginService.class);
    }


}
