package com.lms.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.lms.common.config.LmsConfig;
import com.lms.common.constants.Constants;
import com.lms.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 */
public class AddressUtils {

    private static final Logger logger = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询 url为太平洋IP地址获取IP信息
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        // 开启 开源API查询IP地址归属信息
        if (LmsConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr))
                {
                    logger.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                logger.error("获取地理位置异常 {}", e);
            }
        }
        return UNKNOWN;
    }

}
