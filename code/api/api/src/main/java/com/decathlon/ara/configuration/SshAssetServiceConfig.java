package com.decathlon.ara.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ara.ssh")
public class SshAssetServiceConfig {

    private String host;
    private int port;
    private String user;
    private String password;
    private String remoteHomeFolder;
    private String httpAccess;
    private String screenshotSubFolder;
    private String httpLogsSubFolder;

    // Getters and Setters

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteHomeFolder() {
        return remoteHomeFolder;
    }

    public void setRemoteHomeFolder(String remoteHomeFolder) {
        this.remoteHomeFolder = remoteHomeFolder;
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
