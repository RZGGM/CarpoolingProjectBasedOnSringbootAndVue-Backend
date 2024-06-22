package com.gzasc.onlinecarhailing.pojo;

// 使用枚举类型来记录用户类型
public enum UserTypeEnum {
    // 类型为：乘客
    PASSENGER,
    // 类型为：司机
    DRIVER,
    // 类型为：管理员
    OFFICIAL,
//    其它的不存在的类型。
    NOEXIST;
    public UserTypeEnum valueOf(Integer userType){
        return switch (userType) {
            case 1 -> PASSENGER;
            case 2 -> DRIVER;
            case 3 -> OFFICIAL;
            default -> NOEXIST;
        };

    }
}