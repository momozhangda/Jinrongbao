package com.heaven.srb.oss.service.Impl;

import com.aliyun.oss.model.CannedAccessControlList;
import com.heaven.srb.oss.service.FileService;
import com.heaven.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upLoad(InputStream inputStream, String module, String fileName) {
        OSS ossbuild = new OSSClientBuilder().build(OssProperties.ENDPOINT, OssProperties.KEY_ID, OssProperties.KEY_SECRET);
        if (!ossbuild.doesBucketExist(OssProperties.BUCKET_NAME)){
            ossbuild.createBucket(OssProperties.BUCKET_NAME);
            ossbuild.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        String timeFolder = new DateTime().toString("/yyyy/MM/dd/");
        String key = module+timeFolder+fileName;
        ossbuild.putObject(OssProperties.BUCKET_NAME,key,inputStream);

        ossbuild.shutdown();
        return "https://"+OssProperties.BUCKET_NAME+"."+OssProperties.ENDPOINT+"/"+key;
    }
}
