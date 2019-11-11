package com.withing.rsa;

import com.withing.rsa.api.KeyFactoryApi;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * Package: com.withing.rsa
 * User: 黄伟鑫
 * Email: huangweixin7@jd.com
 * Date: 2019-11-10
 * Time: 21:28
 * Description:
 */
public class Main {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("请输入命令");
        }
        System.out.println(Arrays.asList(Optional.ofNullable(args).orElse(new String[]{""})));
        switch (Optional.ofNullable(args[0]).orElse("")) {
            case "genkey": {
                String filePath = null;
                if (args.length > 2 && StringUtils.isNotBlank(args[1])) {
                    filePath = args[1];
                } else {
                    System.out.println("filePath can't be blank");
                    return;
                }
                KeyFactoryApi factory = KeyFactory.getDefaultKeyFactoryApi();
                if (!factory.generateKey(filePath)) {
                    System.out.println("write key to file error!");
                }
                break;
            }
            case "encode": {
                break;
            }
            case "decode": {
                break;
            }
            default: {

            }
        }

    }

}
