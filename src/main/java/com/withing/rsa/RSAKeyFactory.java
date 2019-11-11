package com.withing.rsa;

import com.withing.rsa.api.KeyFactoryApi;
import com.withing.rsa.domain.RSAPrivateKey;
import com.withing.rsa.domain.RSAPublicKey;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Package: com.withing.rsa
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-10-24
 * Time: 15:59
 * Description:
 */
public class RSAKeyFactory implements KeyFactoryApi {

    private static RSAKeyFactory instance;


    private RSAKeyFactory() {
    }

    public static RSAKeyFactory getInstance() {
        if (instance == null) {
            synchronized (RSAKeyFactory.class) {
                if (instance == null) {
                    instance = new RSAKeyFactory();
                }
                return instance;
            }
        } else {
            return instance;
        }
    }

    @Override
    public boolean generateKey(String path) {
        RSAPrivateKey privateKey = new RSAPrivateKey();
        RSAPublicKey publicKey = privateKey.getPublicKeyInstance();
        return writeKey(privateKey, publicKey, path);
        
    }

    private boolean writeKey(RSAPrivateKey privateKey, RSAPublicKey publicKey, String filePath) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath + "rsa"));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\rsa\\rsa.pub"))) {
            objectOutputStream.writeObject(privateKey);
            outputStream.writeObject(publicKey);
        } catch (IOException e) {
            System.out.println("filePath: " + filePath + "is not exist");
            return false;
        }
        return true;
    }


}
