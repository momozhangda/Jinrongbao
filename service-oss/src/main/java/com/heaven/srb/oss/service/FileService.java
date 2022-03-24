package com.heaven.srb.oss.service;

import java.io.InputStream;

public interface FileService {
    String upLoad(InputStream inputStream,String module,String fileName);
}
