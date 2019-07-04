package com.yao.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExternalStickersDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title; //标签页标题
    private String department;//部门
    private String barCodePath; //条形码
    private String barCode; //条形码标号
    private String area;//区域
    private String userName; //用户名
    private String address; //收获地址
    public ExternalStickersDto(String title,String department,
                   String barCodePath,String barCode,
                   String area,String userName,String address) {
        super();
        this.title = title;
        this.department = department;
        this.barCodePath = barCodePath;
        this.barCode = barCode;
        this.area = area;
        this.userName = userName;
        this.address = address;
    }
    //父类构造器必须声明，避免小几率的异常或无响应
    public ExternalStickersDto() { super(); }
}
