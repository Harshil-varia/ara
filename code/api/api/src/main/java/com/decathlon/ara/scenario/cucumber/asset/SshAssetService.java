/******************************************************************************
 * Copyright (C) 2019 by the ARA Contributors                                 *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *     http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 *                                                                            *
 ******************************************************************************/

package com.decathlon.ara.scenario.cucumber.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.decathlon.ara.configuration.SshAssetServiceConfig;
import com.decathlon.ara.scenario.cucumber.asset.ssh.SshClientHelper;
import com.decathlon.ara.scenario.cucumber.asset.ssh.SshException;

/**
 * Upload to SSH parts of the data from Cucumber and Postman reports.
 */
@Service
@ConditionalOnProperty(name = "ara.adapter.asset.name", havingValue = "ssh")
public class SshAssetService implements AssetService {

    private static final Logger LOG = LoggerFactory.getLogger(SshAssetService.class);

    private final SshAssetServiceConfig sshConfig;
    private final FileNameService fileNameService;

    // Not thread-safe booleans for directory creation
    private boolean screenshotsFolderCreated;
    private boolean httpLogsFolderCreated;

    public SshAssetService(SshAssetServiceConfig sshConfig, FileNameService fileNameService) {
        this.sshConfig = sshConfig;
        this.fileNameService = fileNameService;
    }

    /**
     * Upload a Cucumber scenario screenshot to an SSH server.
     *
     * @param screenshot   the PNG bytes of the screenshot
     * @param scenarioName the name of the scenario for which the screenshot was taken
     * @return the complete URL of the file saved, or null if upload failed
     */
    @Override
    public String saveScreenshot(byte[] screenshot, String scenarioName) {
        return uploadAsset(screenshot, scenarioName, sshConfig.getScreenshotSubFolder(), "png");
    }

    /**
     * Upload a Postman HTTP logs to an SSH server.
     *
     * @param html the HTML representing the HTTP logs
     * @return the complete URL of the file saved, or null if upload failed
     */
    @Override
    public String saveHttpLogs(String html) {
        return uploadAsset(html.getBytes(), "http-log", sshConfig.getHttpLogsSubFolder(), "html");
    }

    private String uploadAsset(byte[] content, String name, String subFolder, String extension) {
        try (SshClientHelper sshClient = connect()) {
            String absoluteFolderPath = sshConfig.getRemoteHomeFolder() + subFolder;

            if (!screenshotsFolderCreated && subFolder.equals(sshConfig.getScreenshotSubFolder())) {
                sshClient.mkdirRecursively(absoluteFolderPath);
                screenshotsFolderCreated = true;
            } else if (!httpLogsFolderCreated && subFolder.equals(sshConfig.getHttpLogsSubFolder())) {
                sshClient.mkdirRecursively(absoluteFolderPath);
                httpLogsFolderCreated = true;
            }

            String fileName = fileNameService.generateReportFileName(name, extension);
            if (extension.equals("png")) {
                sshClient.put(absoluteFolderPath + "/" + fileName, content);
            } else {
                sshClient.echo(absoluteFolderPath + "/" + fileName, new String(content));
            }

            return sshConfig.getHttpAccess() + subFolder + "/" + fileName;
        } catch (SshException e) {
            LOG.warn("SCENARIO|cucumber|Upload failed: {}", e.getMessage(), e);
            return null;
        }
    }

    public SshClientHelper connect() throws SshException {
        return new SshClientHelper(
                sshConfig.getHost(),
                sshConfig.getPort(),
                sshConfig.getUser(),
                sshConfig.getPassword());
    }
}
