package com.example.javastudy.designMode.proxyMode.medium;

import java.util.HashMap;

public class ThirdPartyTencentClass implements ThirdPartyTencentLib{

    @Override
    public HashMap<String, Video> popularVideos() {
        connectToServer("http://v.qq.com/");
        return getRandomVideos();
    }

    @Override
    public Video getVideo(String videoId) {
        connectToServer("http://v.qq.com/" + videoId);
        return getSomeVideo(videoId);
    }

    private int random(int min,int max){
        return min + (int) (Math.random() * (max - min) + 1);
    }

    private void experienceNetworkLatency(){
        int randomLatency = random(5, 10);
        for (int i = 0; i < randomLatency; i++) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void connectToServer(String server){
        System.out.println("Connecting to" + server + "..");
        experienceNetworkLatency();
        System.out.println("Connecting!" + "\n");
    }

    private HashMap<String, Video> getRandomVideos(){
        System.out.println("Downloading populars...");
        experienceNetworkLatency();

        HashMap<String, Video> map = new HashMap<>();
        map.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        map.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        map.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        map.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        map.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));
        System.out.println("Done!" + "\n");
        return map;
    }

    private Video getSomeVideo(String videoId){
        System.out.println("Downloading video...");

        experienceNetworkLatency();
        Video video = new Video(videoId, "Some video title");

        System.out.println("Done!" + "\n");
        return video;
    }
}
