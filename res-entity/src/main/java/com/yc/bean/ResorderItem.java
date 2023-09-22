package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName(value = "resorderitem")
public class ResorderItem implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer roiid;
    private Integer roid;
    private Integer fid;
    private Double dealprice;
    private Integer num;
}
