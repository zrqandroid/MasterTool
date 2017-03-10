package com.zhuruqiao.network;

/**
 * Created by zhuruqiao on 2017/2/7.
 * e-mail:563325724@qq.com
 */

public class DownloadManger {

    public static DownloadManger instance = new DownloadManger();

    private DownloadManger() {

    }

    public void downloadPic() {

    }

    public void downloadVideo() {
        new Thread().start();

    }


}
