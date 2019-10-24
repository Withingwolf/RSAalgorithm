package com.withing.rsa.domain;

import com.withing.rsa.api.PublicKey;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Package: com.withing.rsa.domain
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-10-23
 * Time: 17:55
 * Description:
 */
@Getter
@Accessors(chain = true)
public class RSAPublicKey implements PublicKey, Serializable {
    /**
     * N
     */
    private BigInteger module;
    /**
     * e
     */
    private BigInteger publicExponent;

    public RSAPublicKey() {
    }

    public RSAPublicKey(BigInteger module, BigInteger publicExponent) {
        this.module = module;
        this.publicExponent = publicExponent;
    }

    @Override
    public String encode(String originalText) {
        BigInteger originalFlow = new BigInteger(originalText.getBytes());
        BigInteger result = BigInteger.ONE;
        for (int i = 0; BigInteger.valueOf(i).compareTo(publicExponent) < 0; i++) {
            result = result.multiply(originalFlow).mod(module);
        }
        return new String(originalFlow.toByteArray());
    }
}
