// AraConfiguration.java
package com.decathlon.ara.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConfigurationProperties("ara")
@Import({DataSourceConfiguration.class, SshAssetServiceConfig.class, FileAssetServiceConfig.class})
public class AraConfiguration {

    /**
     * The base URL of the Web client graphical interface (mainly for sent mails to point to the correct URLs).
     */
    private String clientBaseUrl;

    /**
     * Maximum number of days to keep executions.
     * Can be exceeded if and only if it would result in having less that minExecutionsToKeepPerCycle executions left.
     * -1 to disable such limit.
     */
    private Integer maxExecutionDaysToKeep;

    /**
     * Minimum number of jobs to keep per cycleDefinition.
     * This has precedence over maxExecutionDaysToKeep to keep at least a certain number of jobs even if not so many were
     * executed recently.
     * -1 to disable such limit.
     */
    private Integer minExecutionsToKeepPerCycle;

    public String getClientBaseUrl() {
        return clientBaseUrl;
    }

    public void setClientBaseUrl(String clientBaseUrl) {
        this.clientBaseUrl = clientBaseUrl;
    }

    public Integer getMaxExecutionDaysToKeep() {
        return maxExecutionDaysToKeep;
    }

    public void setMaxExecutionDaysToKeep(Integer maxExecutionDaysToKeep) {
        this.maxExecutionDaysToKeep = maxExecutionDaysToKeep;
    }

    public Integer getMinExecutionsToKeepPerCycle() {
        return minExecutionsToKeepPerCycle;
    }

    public void setMinExecutionsToKeepPerCycle(Integer minExecutionsToKeepPerCycle) {
        this.minExecutionsToKeepPerCycle = minExecutionsToKeepPerCycle;
    }
}
