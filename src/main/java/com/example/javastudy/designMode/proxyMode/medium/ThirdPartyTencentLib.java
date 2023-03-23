package com.example.javastudy.designMode.proxyMode.medium;

import java.util.HashMap;

public interface ThirdPartyTencentLib {

    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}
