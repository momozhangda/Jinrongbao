package com.heaven.srb.sms.service.Impl;

import com.heaven.srb.sms.service.SmsService;
import com.heaven.srb.sms.util.SmsProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void send(String[] mobile, String templateCode, Map<String, Object> parm) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SmsProperties.KEY_ID, SmsProperties.KEY_SECRET);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, SmsProperties.REGION_Id, clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
//            String[] phoneNumberSet1 = {"18770922377"};

            req.setPhoneNumberSet(mobile);

            req.setSmsSdkAppId(SmsProperties.SMS_SDKAPP_ID);
            req.setSignName(SmsProperties.SIGN_NAME);
            req.setTemplateId(templateCode);

            String[] code = code();
            for (String mob:mobile) {
                redisTemplate.opsForValue().set(mob,code,5);
            }

//            String[] templateParamSet1 = {"648256"};
            req.setTemplateParamSet(code);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));



        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    private String[] code(){
        Random random = new Random();
        String res = "";
        for (int i = 0; i <6; i++) {
            res+=random.nextInt(10);
        }
        String[] code = new String[1];
        code[0] = res;
        return code;
    }
}
