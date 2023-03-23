package com.example.javastudy.designMode.proxyMode.medium;

import java.util.HashMap;

public class TencentCacheProxy implements ThirdPartyTencentLib{

    private ThirdPartyTencentLib thirdPartyTencentLib;
    private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
    private HashMap<String, Video> cacheAll = new HashMap<String, Video>();

    public TencentCacheProxy() {
        this.thirdPartyTencentLib = new ThirdPartyTencentClass();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = thirdPartyTencentLib.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = thirdPartyTencentLib.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset(){
        cachePopular.clear();
        cacheAll.clear();
    }
}
