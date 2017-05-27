package com.xin.webservice;

import java.util.Properties;

import com.qcloud.cos.sign.Credentials;
import com.zhenhr.tools.PropertiesUtils;

public class CosAccount {
    private static Properties cos_prop = null;

    public CosAccount() {
        if (cos_prop == null) {
            synchronized (CosAccount.class) {
                if (cos_prop == null) {
                    cos_prop = PropertiesUtils.getProperties("qcos.properties");
                }
            }
        }
    }

    public Credentials getCredentials() {
        int appId = Integer.valueOf(cos_prop.getProperty("appId"));
        String secretId = cos_prop.getProperty("secretId");
        String secretKey = cos_prop.getProperty("secretKey");
        Credentials cred = new Credentials(appId, secretId, secretKey);
        return cred;
    }

    public String getFileBucketName() {
        String bucketName = cos_prop.getProperty("file_bucketName");
        return bucketName;
    }

    public String getFileHost() {
        String bucketName = cos_prop.getProperty("file_bucketName");
        String filehost = cos_prop.getProperty("filehost");
        filehost = filehost.replaceAll("#bucketName#", bucketName);
        return filehost;
    }


    public String getUploadFileHost(String bucketName, String uploadFileName) {
        String host = cos_prop.getProperty("uploadfilehost");
        host = host.replaceAll("#bucketName#", bucketName);
        if (uploadFileName.startsWith("/")) {
            host += uploadFileName;
        } else {
            host += "/" + uploadFileName;
        }
        return host;
    }

    public String getRegion() {
        return "tj";
    }

}
