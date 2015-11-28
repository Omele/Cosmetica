package com.boliviabytes.cosmetica.catalogo;

import android.os.Handler;

/**
 * Created by HPIPP on 11/23/2015.
 */
public class WSClient {
    private static WSClient ourInstance = new WSClient();
    public final static String URL = "http://192.168.1.23:8080/WebApp1/SampleWebService?WSDL";
    public static final String NAMESPACE = "http://cos/";
    public static final String SOAP_ACTION_PREFIX = "/";
    public static WSClient getInstance() {
        return ourInstance;
    }

    private WSClient() {

    }
    public void Hello(Handler handler){
        String METHOD = "hello";
        TaskRunner taskRunner=new TaskRunner(handler);
        taskRunner.execute(SOAP_ACTION_PREFIX,URL,METHOD,NAMESPACE);
    }


}
