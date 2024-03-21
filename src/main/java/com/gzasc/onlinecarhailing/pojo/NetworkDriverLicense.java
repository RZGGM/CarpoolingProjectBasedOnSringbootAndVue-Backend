package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.Date;

//网络预约出租汽车驾驶员资格证
@Data
public class NetworkDriverLicense {

    //    在数据库里的唯一标识ID
    private Integer id;

    //    网络预约出租汽车驾驶员资格证号
    private String certificateNo;
    //    网络预约出租汽车驾驶员资格证发证机构
    private String networkCarIssueOrganization;
    //    资格证发证日期
    private Date networkCarIssueDate;
    //    初次领取资格证日期
    private Date getNetworkCarProofDate;
    //    资格证有效起始日期
    private Date networkCarProofOn;
    //    资格证有效截止日期
    private Date networkCarProofOff;

}
