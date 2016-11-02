/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maowubian.tools;

/**
 * 磁盘管理查询工具类
 */
public class StorageUtils {

    private static final long UNIT=1024;
    private static final String B="B";
    private static final String KB="KB";
    private static final String MB="MB";
    private static final String GB="G";

    /**
     * 将内存大小转换成带相应单位的值
     * @param size
     * @return
     */
    public static String convertStorage(long size) {

        if (size>=UNIT){        //B


        }else if (size>=UNIT<<8){//KB

        }else if (size>=UNIT<<16){//MB

        }else if (size>=UNIT<<24){//G

        }else {
            return size+" B";
        }

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }






}
