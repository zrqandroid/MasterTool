package com.maowubian.tools.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhuruqiao on 2017/1/4.
 * e-mail:563325724@qq.com
 */

public class IOUtils {


    public enum CodingType {
        GBK("gbk", 0),
        UTF_8("utf-8", 1);

        public final String name;
        public final int index;

        CodingType(String name, int index) {
            this.name = name;
            this.index = index;

        }

    }

    public InputStream getInputStream(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                return fileInputStream;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取文件获取字符串
     *
     * @param path
     * @param codeType
     * @return
     */
    public String readFile(String path, CodingType codeType) {

        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(getInputStream(path), codeType.name);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }


}
