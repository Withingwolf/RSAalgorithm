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
    public void generateKey() {
        RSAPrivateKey privateKey = new RSAPrivateKey();
        RSAPublicKey publicKey = privateKey.getPublicKeyInstance();
        writePrivateKey(privateKey);
        writePublicKey(publicKey);
    }

    private void writePrivateKey(RSAPrivateKey privateKey) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("rsa/rsa"));) {
            objectOutputStream.writeObject(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (PrintWriter printWriter = new PrintWriter(new File("rsa/rsa"));) {
//            printWriter.print(privateKey.getModule());
//            printWriter.print("\n");
//            printWriter.print(privateKey.getPublicExponent());
//            printWriter.print("\n");
//            printWriter.print(privateKey.getPrivateExponent());
//            printWriter.print("\n");
//            printWriter.print(privateKey.getPrime1());
//            printWriter.print("\n");
//            printWriter.print(privateKey.getPrime2());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private void writePublicKey(RSAPublicKey publicKey) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("rsa/rsa.pub"))){
            outputStream.writeObject(publicKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (PrintWriter printWriter = new PrintWriter(new File("rsa/rsa.pub"))) {
//            printWriter.print(publicKey.getModule());
//            printWriter.print("\n");
//            printWriter.print(publicKey.getPublicExponent());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

}
