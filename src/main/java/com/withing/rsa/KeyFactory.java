package com.withing.rsa;

import com.google.common.collect.Maps;
import com.withing.rsa.api.KeyFactoryApi;
import com.withing.rsa.eum.KeyType;

import java.util.Map;

/**
 * Package: com.withing.rsa
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-10-22
 * Time: 17:23
 * Description:
 */
public class KeyFactory {

    private final static Map<KeyType, KeyFactoryApi> KEY_FACTORY_MAP = Maps.newHashMap();

    static {
        KEY_FACTORY_MAP.put(KeyType.RSA, RSAKeyFactory.getInstance());
    }

    public static KeyFactoryApi getDefaultKeyFactoryApi() {
        return KEY_FACTORY_MAP.get(KeyType.RSA);
    }

}
