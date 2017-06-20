package com.xin.webservice;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Sign;
import com.xin.system.dao.FileUploadDao;
import com.zhenhr.tools.Base64;
import com.zhenhr.tools.JsonUtil;
import com.zhenhr.tools.PropertiesUtils;
import com.zhenhr.tools.RandomUtils;
import org.json.JSONObject;

/**
 * @author guoyongshi
 */
public class COSClientService {

    /**
     * 创建签名
     *
     * @param fileType
     * @return
     */
    public static FileUploadDao createFileSign(String fileType) {
        String path = "/default";

        String fileName = createFileName(fileType);
        fileName = path + "/" + fileName;

        FileUploadDao dao = new FileUploadDao();
        dao.setKey(fileName);

        CosAccount account = new CosAccount();
        long expired = System.currentTimeMillis() / 1000 + 600;
        try {
            String bucketName = account.getFileBucketName();
            String signStr = Sign.getPeriodEffectiveSign(bucketName, fileName,
                    account.getCredentials(), expired);
            dao.setSign(signStr);
            dao.setUrl(account.getFileHost() + fileName);
            dao.setUploadUrl(account.getUploadFileHost(bucketName, fileName));
            return dao;
        } catch (AbstractCosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建企业上传文件
     *
     * @param companyId
     * @param folder
     * @param fileName
     * @param fileType
     * @return
     */
    public static FileUploadDao createFileSign(Long companyId, String folder,
                                               String fileName, String fileType) {
        // 创建目录
        if (folder != null) {
            if (!folder.startsWith("/")) {
                folder = "/" + folder;
            }
            if (!folder.endsWith("/")) {
                folder += "/";
            }

            if (companyId != null) {
                folder += companyId + "/";
            }
            String result = COSClientService.createFolder(folder + companyId);
        }

        // 获取文件名称
        if (fileName == null) {
            fileName = createFileName(fileType);
        } else {
            if (!fileName.endsWith(fileType)) {
                if (fileType.startsWith(".")) {
                    fileName += fileType;
                } else {
                    fileName += "." + fileType;
                }
            }
        }
        if (folder != null) {
            fileName = folder + fileName;
        } else {
            fileName = "/default/" + fileName;
        }

        FileUploadDao dao = new FileUploadDao();
        dao.setKey(fileName);

        CosAccount account = new CosAccount();
        long expired = System.currentTimeMillis() / 1000 + 600;
        try {
            String bucketName = account.getFileBucketName();
            String signStr = Sign.getPeriodEffectiveSign(bucketName, fileName,
                    account.getCredentials(), expired);
            dao.setSign(signStr);
            dao.setUrl(account.getFileHost() + fileName);
            dao.setUploadUrl(account.getUploadFileHost(bucketName, fileName));
            return dao;
        } catch (AbstractCosException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleFile(String path) {

    }

    /**
     * 创建path
     *
     * @return
     */
    public static String createFolder(String folder) {
        CosAccount account = new CosAccount();
        COSClient cosClient = getClient();

        if (!folder.endsWith("/")) {
            folder += "/";
        }
        CreateFolderRequest request = new CreateFolderRequest(account.getFileBucketName(),
                folder);
        String result = cosClient.createFolder(request);
        return result;
    }


    private static COSClient getClient() {
        CosAccount account = new CosAccount();

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(account.getRegion());

        COSClient cosClient = new COSClient(clientConfig, account.getCredentials());
        return cosClient;
    }

    private static String createFileName(String fileType) {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        fileName = fileName + RandomUtils.generateNumber(5);
        if (fileType.startsWith(".")) {
            fileName += fileType;
        } else {
            fileName += "." + fileType;
        }
        return fileName;
    }

    public static void testUpload() {
        Properties prop = PropertiesUtils.getProperties("qcos.properties");
        int appId = Integer.valueOf(prop.getProperty("appId"));
        String secretId = prop.getProperty("secretId");
        String secretKey = prop.getProperty("secretKey");
        String bucketName = prop.getProperty("file_bucketName");
        String filehost = prop.getProperty("filehost");
        filehost = filehost.replaceAll("#bucketName#", bucketName);

        COSClient cosClient = new COSClient(appId, secretId, secretKey);

        String cosFilePath = "/sample_file.txt";
        String localFilePath1 = "/Volumes/work/deploy.sh";
        UploadFileRequest uploadFileRequest = new
                UploadFileRequest(bucketName, cosFilePath, localFilePath1);
        String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
        JSONObject uploadFileJson = new JSONObject(uploadFileRet);
    }

    public static void main(String[] args) {
//        Integer a = 10;
//        Integer b = 10;
//        Long l = 20L;
//        Integer c = 200;
//        Integer d = 200;
//        System.out.println(a == b);
//
//        System.out.println(c.equals(d));
//
//        System.out.println(l == a+b);
//        System.out.println(l.equals( a+b));


    }
}
