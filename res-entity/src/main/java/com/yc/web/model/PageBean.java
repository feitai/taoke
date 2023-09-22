package com.yc.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    //下面两个属性是界面给的已知参数
    private int pageno =1;  //当前是第几页
    private int pagesize=5;  // 每页有多少条
    private String sortby; // 排序的列名
    private String sort;  // 取值为  ： asc/desc


    //  以下两个属性是数据库查询出来的结果
    private long total;  //记录总数
    private List<T> dataset; //  需要在业务层中进行计算

    private int totalpages;  // 总共有多少页
    private int pre;  // 上一页的页数

    private  int next; // 下一页的页数
}
