package com.decathlon.ara.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ara.file")
public class FileAssetServiceConfig {

    private String homeFolder;
    private String httpAccess;
    private String screenshotSubFolder;
    private String httpLogsSubFolder;

    // Getters and Setters

    public String getHomeFolder() {
        return homeFolder;
    }

    public void setHomeFolder(String homeFolder) {
        this.homeFolder = homeFolder;
    }

    public String getHttpAccess() {
        return httpAccess;
    }

    public void setHttpAccess(String httpAccess) {
        this.httpAccess = httpAccess;
    }

    public String getScreenshotSubFolder() {
        return screenshotSubFolder;
    }

    public void setScreenshotSubFolder(String screenshotSubFolder) {
        this.screenshotSubFolder = screenshotSubFolder;
    }

    public String getHttpLogsSubFolder() {
        return httpLogsSubFolder;
    }

    public void setHttpLogsSubFolder(String httpLogsSubFolder) {
        this.httpLogsSubFolder = httpLogsSubFolder;
    }
}
