package cn.xzxy.lewy.dscross.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveDataSourceReq {
    private String datasourceId;

    @NotBlank(message = "数据源名称不能为空")
    private String datasourceName;

    @NotNull(message = "数据源规模不能为空")
    private Integer datasourceScope;

    @NotBlank(message = "数据源类型不能为空 1：业务数据 2: 元数据")
    private String datasourceType;

    @NotBlank(message = "数据库类型不能为空")
    private String databaseType;

    @NotBlank(message = "数据源IP不能为空")
    private String databaseIp;

    @NotBlank(message = "数据源端口不能为空")
    private String databasePort;

    @NotBlank(message = "数据库名称不能为空")
    private String databaseLabel;

    @NotBlank(message = "数据库账号不能为空")
    private String databaseUsername;

    private String databasePassword;

    private String prestoName;

    @NotBlank(message = "数据源url不能为空")
    private String databaseUrl;

    private String datasourceDesc;

    // 关联的业务数据源Id
    // datasourceFlag为2时不能为空
    private String mappingDsId;

    @NotBlank(message = "是否配置元数据源标志不能为空")
    private String configMetaFlag;

    // configMetaFlag为1时不能为空
    private String metaDsId;
}
