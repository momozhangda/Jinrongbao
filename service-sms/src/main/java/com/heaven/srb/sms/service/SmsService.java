package com.heaven.srb.sms.service;

import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface SmsService {
    void send(String[] mobile, String templateCode, Map<String,Object> parm);
}
