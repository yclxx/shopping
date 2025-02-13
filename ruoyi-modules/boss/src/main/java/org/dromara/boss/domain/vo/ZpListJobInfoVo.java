package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * boos招聘 招聘信息 zpListData 中的对象
 *
 * @author xiexi
 * @description
 * @date 2024/11/9 18:30
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpListJobInfoVo {
    /**
     * 安全id 每次请求都会变
     */
    private String securityId;
    /**
     * boos 头像
     */
    private String bossAvatar;
    private Long bossCert;
    /**
     * 加密 boosId
     */
    private String encryptBossId;
    /**
     * boos 名称
     */
    private String bossName;
    private String bossTitle;
    private Long goldHunter;
    /**
     * boss 是否在线
     */
    private Boolean bossOnline;
    /**
     * 加密 jobId
     */
    private String encryptJobId;
    private Long expectId;
    /**
     * 职位名称
     */
    private String jobName;
    /**
     * 请求 需要，具体啥意思不太清楚
     */
    private String lid;
    /**
     * 薪资描述
     */
    private String salaryDesc;
    /**
     * 职位标签
     */
    private String[] jobLabels;
    private Long jobValidStatus;
    /**
     * icon 描述 ，例如 急聘
     */
    private String iconWord;
    /**
     * 职位技能
     */
    private String[] skills;
    /**
     * 招聘年限
     */
    private String jobExperience;
    private String daysPerWeekDesc;
    private String leastMonthDesc;
    /**
     * 文凭要求
     */
    private String jobDegree;
    /**
     * 城市
     */
    private String cityName;
    /**
     * 区域
     */
    private String areaDistrict;
    /**
     * 商圈 街道
     */
    private String businessDistrict;
    private Long jobType;
    private Long proxyJob;
    private Long proxyType;
    private Long anonymous;
    private Long outland;
    private Long optimal;
    private Long[] iconFlagList;
    private Long itemId;
    private Long city;
    private Long isShield;
    private Boolean atsDirectPost;
    private Object gps;
    /**
     * 加密 品牌id
     */
    private String encryptBrandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌logo
     */
    private String brandLogo;
    /**
     * 融资阶段
     */
    private String brandStageName;
    /**
     * 行业
     */
    private String brandIndustry;
    /**
     * 公司规模
     */
    private String brandScaleName;
    /**
     * 福利待遇
     */
    private String[] welfareList;
    private Long industry;
    private Boolean contact;
    private Boolean showTopPosition;
}
