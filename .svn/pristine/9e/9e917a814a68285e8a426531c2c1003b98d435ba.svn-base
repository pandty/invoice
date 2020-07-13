package com.invoice.system.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String  url = "https://paymgmt.shuzutech.com";
    private static final String  appid = ""; //自己的appid
    private static final String  appSecret = ""; //自己的appsecret

    private String access_token;

    public String token(){
        String path = "/invoice/token";
        String para = "appId=" + appid + "&appSecret=" + appSecret;
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return HttpUtil.httpRequest(url + path , para ,headers);
    }

    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }

    public String getAccess_token(){
        return this.access_token;
    }

    public String DQFPXX(String jsbh, String fplxdm){
        String path = "/invoice/business";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"DQFPXX\"><body><input><jsbh>"+jsbh+"</jsbh><fplxdm>" +fplxdm+"</fplxdm></input></body></business>";
        return bussness(path, xml);
    }

    public String bussness(String path, String body){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(dNow);

        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(body + date + getAccess_token()).getBytes());
        Map<String,String> headers = new HashMap<String,String>();

        headers.put("AppId",appid);
        headers.put("Date",date);
        headers.put("Content-MD5",sign);
        headers.put("Content-Type", "application/xml");

        return HttpUtil.httpRequest(url + path , body ,headers);

    }




    public static void main( String[] args )
    {
        App demo = new App();
        //System.out.println(demo.token());
        //<business id="TOKEN"><body><returnmsg>获取成功</returnmsg><returncode>0</returncode><access_token>30ff28ea65e2630287a73eb7bca9bf8b</access_token><expires>7200</expires></body></business>

        demo.setAccess_token("30ff28ea65e2630287a73eb7bca9bf8b");
        String response = demo.DQFPXX("110101201707010037~~499000152093","004");
        System.out.println(response);
    }
}
