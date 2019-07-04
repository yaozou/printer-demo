package com.yao.test;

import java.io.Serializable;
import java.util.Date;

/**
 * demo测试数据封装
 *
 * @author xuyong
 *
 */
public class DemoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String assetName;		//资产名称

    private String assetType;		//型号

    private String deptName;		//部门

    private String responsible;		//责任人

    private Date useTime;			//启用日期

    private String qrCodePath;		//二维码路径

    private String title;			//标签页标题

    public DemoDto(String assetName, String assetType, String deptName,
                   String responsible, Date useTime, String qrCodePath, String title) {
        super();
        this.assetName = assetName;
        this.assetType = assetType;
        this.deptName = deptName;
        this.responsible = responsible;
        this.useTime = useTime;
        this.qrCodePath = qrCodePath;
        this.title = title;
    }

    //父类构造器必须声明，避免小几率的异常或无响应
    public DemoDto() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

}
