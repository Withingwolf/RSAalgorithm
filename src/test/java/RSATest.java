import com.withing.rsa.KeyFactory;
import com.withing.rsa.api.KeyFactoryApi;
import com.withing.rsa.domain.RSAPrivateKey;
import com.withing.rsa.domain.RSAPublicKey;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;

/**
 * Package: PACKAGE_NAME
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-10-24
 * Time: 18:23
 * Description:
 */
public class RSATest {

    public static void main(String[] args) {
        KeyFactoryApi factoryApi = KeyFactory.getDefaultKeyFactoryApi();
        factoryApi.generateKey();
        RSAPublicKey publicKey = null;
        RSAPrivateKey privateKey = null;
        try (
                ObjectInputStream publicStream = new ObjectInputStream(new FileInputStream("rsa/rsa.pub"));
                ObjectInputStream privateStream = new ObjectInputStream(new FileInputStream("rsa/rsa"))
        ) {
            publicKey = (RSAPublicKey) publicStream.readObject();
            privateKey = (RSAPrivateKey) privateStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String originText = "RSA加密原文";

        String cipherText = publicKey.encode(originText);
        String decodeText = privateKey.decode(cipherText);
        System.out.println(decodeText);
    }

}
