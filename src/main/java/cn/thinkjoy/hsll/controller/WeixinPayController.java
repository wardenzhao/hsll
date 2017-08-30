package cn.thinkjoy.hsll.controller;

import cn.thinkjoy.hsll.util.HttpKit;
import cn.thinkjoy.hsll.util.JsonUtil;
import cn.thinkjoy.hsll.util.StringUtil;
import cn.thinkjoy.hsll.weixin.WXPayConstants;
import cn.thinkjoy.hsll.weixin.WXPayUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by warden on 17/8/24.
 */
@Scope("prototype")
@Controller("weixinPayController")
@RequestMapping(value = "/hsll")
public class WeixinPayController {

    private String appid = "wx1bed28867ce8a1ad";
    private String partnerid = "1487368312";
    private String paternerKey = "ff13bdd9c73308bc7495067ca9b58106";
    private String notifyUrl = "http://www.100fruit.cn/hsll/paysuccess";


    @RequestMapping(value = "/pay")
    public void jspay(HttpServletRequest request, HttpServletResponse response, String callback) throws Exception {
        // 获取openid
        String openId = request.getParameter("openId");

        String out_trade_no = request.getParameter("orderNo");

        String nonce_str = create_nonce_str();
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("appid", appid);
        paraMap.put("attach", "测试支付");
        paraMap.put("body", "测试购买Beacon支付");
        paraMap.put("mch_id", partnerid);
        paraMap.put("nonce_str", nonce_str);
        paraMap.put("openid", openId);
        paraMap.put("out_trade_no", out_trade_no);
        paraMap.put("spbill_create_ip", getAddrIp(request));
        paraMap.put("total_fee", "1");
        paraMap.put("trade_type", "JSAPI");
        paraMap.put("notify_url", notifyUrl);
        paraMap.put("device_info", "WEB");

//        Map<String, String> signMap = new HashMap<String, String>();
//        signMap.put("appid", appid);
//        signMap.put("body", "测试购买Beacon支付");
//        signMap.put("mch_id", partnerid);
//        signMap.put("device_info", "WEB");
//        paraMap.put("nonce_str", nonce_str);
        String sign = WXPayUtil.generateSignature(paraMap, paternerKey, WXPayConstants.SignType.MD5);
        paraMap.put("sign", sign);

        // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        String xml = ArrayToXml(paraMap, false);
        System.out.println("pay xml--->" + xml);
        String xmlStr = HttpKit.post(url, xml);

        System.out.println("pay return xml--->" + xmlStr);
        // 预付商品id
        String prepay_id = "";

        if (xmlStr.indexOf("SUCCESS") != -1) {
            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
            prepay_id = (String) map.get("prepay_id");
        }

        Map<String, String> payMap = new HashMap<String, String>();
        payMap.put("appId", appid);
        payMap.put("timeStamp", create_timestamp());
        payMap.put("nonceStr", create_nonce_str());
        payMap.put("signType", "MD5");
        payMap.put("package", "prepay_id=" + prepay_id);
        String paySign = WXPayUtil.generateSignature(payMap,paternerKey, WXPayConstants.SignType.MD5);

        payMap.put("pg", prepay_id);
        payMap.put("paySign", paySign);

        // 拼接并返回json

        System.out.println("return json--->" + JsonUtil.tranObjectToJsonStr(payMap).toString());
        response.getWriter().print(JsonUtil.tranObjectToJsonStr(payMap).toString());
        response.getWriter().close();
    }

    /**
     * 支付成功回调方法
     * @param request
     * @param response
     * @param callback
     * @throws Exception
     */
    @RequestMapping(value = "/paysuccess")
    public void paysuccess(HttpServletRequest request, HttpServletResponse response, String callback) throws Exception {

        //读取参数
        InputStream inputStream ;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s ;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null){
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = WXPayUtil.xmlToMap(sb.toString());

        //过滤空 设置 TreeMap
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if(null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        System.out.println(packageParams);
        //判断签名是否正确
        if(WXPayUtil.isSignatureValid(m, paternerKey, WXPayConstants.SignType.MD5)) {
            //------------------------------
            //处理业务开始
            //------------------------------
            String resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))){
                // 这里是支付成功
                //////////执行自己的业务逻辑////////////////
                String mch_id = (String)packageParams.get("mch_id");
                String openid = (String)packageParams.get("openid");
                String is_subscribe = (String)packageParams.get("is_subscribe");
                String out_trade_no = (String)packageParams.get("out_trade_no");

                String total_fee = (String)packageParams.get("total_fee");

                System.out.println("mch_id:" + mch_id);
                System.out.println("openid:" + openid);
                System.out.println("is_subscribe:" + is_subscribe);
                System.out.println("out_trade_no:" + out_trade_no);
                System.out.println("total_fee:" + total_fee);

                //////////执行自己的业务逻辑////////////////

                System.out.println("支付成功");
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

            } else {
                System.out.println("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            //------------------------------
            //处理业务完毕
            //------------------------------
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else{
            System.out.println("通知签名验证失败");
        }

    }
    /**
     * map转成xml
     *
     * @return
     */
    public String ArrayToXml(Map<String, String> parm, boolean isAddCDATA) {
        StringBuffer strbuff = new StringBuffer("<xml>");
        if (parm != null && !parm.isEmpty()) {
            for (Map.Entry<String, String> entry : parm.entrySet()) {
                strbuff.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA) {
                    strbuff.append("<![CDATA[");
                    if (StringUtil.isNotEmpty(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                    strbuff.append("]]>");
                } else {
                    if (StringUtil.isNotEmpty(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                }
                strbuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strbuff.append("</xml>").toString();
    }

    private String create_nonce_str() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    private String getAddrIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    private String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
