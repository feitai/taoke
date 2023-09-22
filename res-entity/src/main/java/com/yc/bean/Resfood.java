package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "resfood")
public class Resfood implements Serializable {
    @TableId(type = IdType.AUTO)
    private   Integer fid;
    private  String fname;
    private  Double normprice;
    private Double realprice;
    private String detail;
    private String fphoto;

        //是存储在redis中的数据，意为点赞数，该注解表示，这个属性在mybatis plus 扫描时不会当作该类的一个属性
    @TableField(select = false)
    private  Long praise;
}
