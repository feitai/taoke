package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "resorder")
public class Resorder {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private String ordertime;
    private String deliverytime;
    private String ps;
    private Integer status;

}
