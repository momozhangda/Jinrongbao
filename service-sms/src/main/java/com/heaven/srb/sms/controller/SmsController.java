package com.heaven.srb.sms.controller;


import com.heaven.common.result.R;
import com.heaven.srb.sms.service.SmsService;
import com.heaven.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "短信管理")
@CrossOrigin
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    @PostMapping("/send")
    public R sendSms(String mobile){
        if (mobile == null){
            return R.error().message("手机号为空");
        }
        if(mobile.length()!=11 && mobile.charAt(0)!=1){
            return R.error().message("手机号格式异常");
        }

        String[] mob = new String[1];
        mob[0] = mobile;
        try {
            Map<String, Object> map = new HashMap<>();
            smsService.send(mob, SmsProperties.TEMPLATE_CODE,map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().message("验证码已发送");

    }
}
