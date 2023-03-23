package com.example.javastudy.designMode.proxyMode.medium;


public class Main {

    /**
     * 缓存代理：代理模式有助于实现延迟初始化，并且对低效的第三方Twitter集成程序库进行缓存
     */
    public static void main(String[] args) {
        TencentDownloader naiveDownloader = new TencentDownloader(new ThirdPartyTencentClass());
        TencentDownloader smartDownloader = new TencentDownloader(new TencentCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);
        System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");
    }

    private static long test(TencentDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");
        // Users might visit the same page quite often.
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n");
        return estimatedTime;
    }
}
