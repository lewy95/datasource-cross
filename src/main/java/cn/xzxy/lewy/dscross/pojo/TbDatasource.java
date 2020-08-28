package cn.xzxy.lewy.dscross.pojo;

import java.util.Date;

public class TbDatasource {
    private String datasourceId;

    private String datasourceName;

    private Integer datasourceScope;

    private String datasourceDesc;

    private String datasourceType;

    private String databaseLabel;

    private String databaseIp;

    private String databasePort;

    private String databaseUrl;

    private String databaseType;

    private String databaseUsername;

    private String databasePassword;

    private String prestoName;

    private String configMetaFlag;

    private String metaDsId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName == null ? null : datasourceName.trim();
    }

    public Integer getDatasourceScope() {
        return datasourceScope;
    }

    public void setDatasourceScope(Integer datasourceScope) {
        this.datasourceScope = datasourceScope;
    }

    public String getDatasourceDesc() {
        return datasourceDesc;
    }

    public void setDatasourceDesc(String datasourceDesc) {
        this.datasourceDesc = datasourceDesc == null ? null : datasourceDesc.trim();
    }

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    public String getDatabaseLabel() {
        return databaseLabel;
    }

    public void setDatabaseLabel(String databaseLabel) {
        this.databaseLabel = databaseLabel == null ? null : databaseLabel.trim();
    }

    public String getDatabaseIp() {
        return databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp == null ? null : databaseIp.trim();
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort == null ? null : databasePort.trim();
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl == null ? null : databaseUrl.trim();
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType == null ? null : databaseType.trim();
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername == null ? null : databaseUsername.trim();
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword == null ? null : databasePassword.trim();
    }

    public String getPrestoName() {
        return prestoName;
    }

    public void setPrestoName(String prestoName) {
        this.prestoName = prestoName == null ? null : prestoName.trim();
    }

    public String getConfigMetaFlag() {
        return configMetaFlag;
    }

    public void setConfigMetaFlag(String configMetaFlag) {
        this.configMetaFlag = configMetaFlag == null ? null : configMetaFlag.trim();
    }

    public String getMetaDsId() {
        return metaDsId;
    }

    public void setMetaDsId(String metaDsId) {
        this.metaDsId = metaDsId == null ? null : metaDsId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}