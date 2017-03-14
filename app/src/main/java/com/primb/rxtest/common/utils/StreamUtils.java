package com.primb.rxtest.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * PACKAGE_NAME: com.tomato.z.zxweather.common.utils
 * FUNCTIONAL_DESCRIPTION:
 * CREATE_BY: 尽际
 * CREATE_TIME: 16/1/16 下午2:52
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME:
 */
public class StreamUtils {
    public static String inputStream2String(InputStream in) throws IOException {

        /**
         * String fileName = fileName; String packetName =
         * context.getPackageName(); 将fileName 转换成id int resId =
         * context.getResources().getIdentifier(fileName, "raw", packetName);
         * ObjectInputStream ois = null; InputStream im =
         * context.getResources().openRawResource(resId);
         * 其中getIdentifier三参数分别是：文件名，资源所在文件夹名（如：drawable, raw,），包路径
         */

        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = read.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                    read = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
