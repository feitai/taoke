package com.yc.resfoods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.Resfood;
import com.yc.resfoods.biz.ResfoodBiz;
import com.yc.web.model.PageBean;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("resfood")
@Slf4j
@Api(tags = "菜品管理")
public class ResfoodController {
    @Autowired
    private ResfoodBiz resfoodBiz;
    public Set<Thread> set = new HashSet<Thread>();

    @GetMapping("findById/{fid}")
    // {fid}路由参数
    @ApiOperation(value = "根据菜品编号查询操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid", value = "菜品号", required = true)
    })
    public Map<String, Object> findById(@PathVariable Integer fid) throws InterruptedException {



        Map<String, Object> map = new HashMap<>();
        Resfood rf = null;
        try {
            rf = this.resfoodBiz.findById(fid);
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("code", 0);
            map.put("msg", ex.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("obj", rf);

        return map;
    }




    @GetMapping("findAll")
    @ApiOperation(value = "查询所有菜品")
//    @SentinelResource(value = "hotkey-page",fallback = "exceptionFallback")
    public Map<String, Object> findAll() {


        Thread thread= Thread.currentThread();
        set.add(thread);
        log.info(" 线程数为"+set.size()  + "当前线程编号为" +thread.getId());

//        try {
//            Thread.sleep(20000);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Map<String, Object> map = new HashMap<>();
        List<Resfood> list = null;

        try {
            list = this.resfoodBiz.findAll();
        } catch (Exception ex) {
            map.put("code", 0);
            map.put("msg", ex.getCause());
            ex.printStackTrace();
            return map;
        }

        map.put("code", 1);
        map.put("obj", list);
        return map;
    }

    @GetMapping("findByPage")
//    @SentinelResource(value = "hotkey-page",blockHandler = "handleBlock")
    @ApiOperation(value = "分页查询操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageno", value = "页号", required = true),
            @ApiImplicitParam(name = "pagesize", value = "每页记录数", required = true),
            @ApiImplicitParam(name = "sortby", value = "排序列", required = true),
            @ApiImplicitParam(name = "sort", value = "排序方式", required = true)
    })
    public Map<String, Object> findByPage(@RequestParam int pageno, @RequestParam int pagesize,
                                          @RequestParam String sortby, @RequestParam String sort) {
        Map<String, Object> map = new HashMap<>();
        Page<Resfood> page = null;

        try {
            page = this.resfoodBiz.findByPage(pageno, pagesize, sortby, sort);
        } catch (Exception ex) {
            map.put("code", 0);
            map.put("msg", ex.getCause());
            return map;
        }
        map.put("code", 1);
        PageBean pageBean = new PageBean();
        pageBean.setPageno(pageno);
        pageBean.setPagesize(pagesize);
        pageBean.setSort(sort);
        pageBean.setSortby(sortby);
        pageBean.setTotal(page.getTotal());
        pageBean.setDataset(page.getRecords());
        int totalPages = (int) Math.ceil((double) page.getTotal() / pageBean.getPagesize());
        pageBean.setTotalpages(totalPages);
        if (pageBean.getPageno() <= 1) {
            pageBean.setPre(1);
        } else {
            pageBean.setPre(pageBean.getPageno() - 1);
        }
        if (pageBean.getPageno() == totalPages) {
            pageBean.setNext(totalPages);
        } else {
            pageBean.setNext(pageBean.getPageno() + 1);
        }
        map.put("data", pageBean);
        return map;
    }

    private Map<String,Object> handleBlock(@RequestParam int pageno, @RequestParam int pagesize,
                                           @RequestParam String sortby, @RequestParam String sort, BlockException exception){
        exception.printStackTrace();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg",exception.getRuleLimitApp()+":+ 被限流了，规则为："+exception.getRule());
        return map;  //sentinel 200

    }
    private Map<String,Object> exceptionFallback(Throwable t){
        t.printStackTrace();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","exception 业务错误:  "+t.getCause());
        return map;  //sentinel 200
    }
}









