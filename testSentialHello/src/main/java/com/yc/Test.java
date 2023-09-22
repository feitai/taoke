package com.yc;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 初始化流控规则
        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                // 尝试获取资源的访问权限
                entry = SphU.entry("HelloWorld");
                /*您的业务逻辑 - 开始*/
                System.out.println("hello world");
                /*您的业务逻辑 - 结束*/
            } catch (BlockException e1) {
                /*流控逻辑处理 - 开始*/
                // 被流控时的处理逻辑
                System.out.println("block!");
                /*流控逻辑处理 - 结束*/
            } finally {
                if (entry != null) {
                    // 退出资源访问权限的保护，释放资源
                    entry.exit();
                }
            }
        }
    }

    // 初始化流控规则
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld"); // 设置受保护的资源名
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 设置流控的维度为QPS（每秒查询次数）
        rule.setCount(20); // 设置限制的QPS阈值为20
        rules.add(rule);
        FlowRuleManager.loadRules(rules); // 加载流控规则
    }
}
