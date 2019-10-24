package com.withing.rsa.domain;

import com.withing.rsa.api.PrivateKey;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

/**
 * Package: com.withing.rsa.domain
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-10-24
 * Time: 10:49
 * Description:
 */
@Getter
@Accessors(chain = true)
public class RSAPrivateKey implements PrivateKey, Serializable {
    /**
     * N
     */
    private BigInteger module;
    /**
     * e
     */
    private BigInteger publicExponent;
    /**
     * d
     */
    private BigInteger privateExponent;
    /**
     * p
     */
    private BigInteger prime1;
    /**
     * q
     */
    private BigInteger prime2;
    /**
     * d mod(p-1)
     */
    private BigInteger exponent1;
    /**
     * d mod (q-1)
     */
    private BigInteger exponent2;
    /**
     * q-1 mod p
     */
    private BigInteger coefficient;

    Process process = new Process();


    public RSAPrivateKey(BigInteger p1, BigInteger p2) {
        initPrivateKey(p1, p2);
    }

    public RSAPrivateKey() {
        initPrivateKey(process.generateHugePrime(), process.generateHugePrime());
    }

    public RSAPublicKey getPublicKeyInstance() {
        return new RSAPublicKey(module, publicExponent);
    }

    private void initPrivateKey(BigInteger p1, BigInteger p2) {
        prime1 = p1;
        prime2 = p2;
        this.module = prime1.multiply(prime2);
        publicExponent = BigInteger.valueOf(65537);
        privateExponent = process.genPrivateExponent();
        exponent1 = privateExponent.mod(prime1.subtract(BigInteger.ONE));
        exponent2 = privateExponent.mod(prime2.subtract(BigInteger.ONE));
        coefficient = prime2.subtract(BigInteger.ONE).mod(prime1);
    }

    @Override
    public String decode(String cipherText) {
        BigInteger cipherFlow = new BigInteger(cipherText.getBytes());
        BigInteger result = BigInteger.ONE;
        for (int i = 0; BigInteger.valueOf(i).compareTo(privateExponent) < 0; i++) {
            result = result.multiply(cipherFlow).mod(module);
        }
        return new String(cipherFlow.toByteArray());
    }

    private class Process implements Serializable {
        private BigInteger x;
        private BigInteger y;

        private BigInteger generateHugePrime() {
            return generateHugePrime(300);
        }

        private BigInteger generateHugePrime(int size) {
            BigInteger bigInteger = BigInteger.probablePrime(300, new Random());
            while (!bigInteger.isProbablePrime(1)) {
                bigInteger = bigInteger.nextProbablePrime();
            }
            return bigInteger;
        }

        private int gcd(int a, int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, b % a);
        }

        BigInteger genPrivateExponent() {
            return exgcd(publicExponent, exgcd(publicExponent, prime1.subtract(BigInteger.ONE).multiply(prime2.subtract(BigInteger.ONE))));
        }

        private BigInteger exgcd(BigInteger a, BigInteger b) {
            if (b.equals(BigInteger.ZERO)) {
                x = BigInteger.ONE;
                y = BigInteger.ZERO;
                return a;
            }
            BigInteger r = exgcd(b, a.mod(b));
            BigInteger t = x;
            x = y;
            y = t.subtract(a.divide(b).multiply(y));
            return r;
        }
    }

}
