package com.lms.api.system;

import com.lms.common.api.ResultVO;
import com.lms.common.constants.Constants;
import com.lms.common.core.controller.BaseController;
import com.lms.common.utils.IdUtils;
import com.lms.common.utils.VerifyCodeUtils;
import com.lms.common.utils.sign.Base64;
import com.lms.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 */
@RestController
public class CaptchaController extends BaseController {

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResultVO getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uuid", uuid);
            map.put("img", Base64.encode(stream.toByteArray()));
            return new ResultVO<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        } finally {
            stream.close();
        }
    }

}
