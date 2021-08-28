package com.zk.test;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderZk
 */
public class HelloWorld {

    public static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("helloworld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. 配置规则
        initFlowRules();

        // 2. 定义资源
        while (true) {
            Entry entry = null;
            try {
                // 2.1 定义资源名称
                entry = SphU.entry("helloworld");

                // 2.2 执行资源逻辑代码
                System.out.println("helloworld: 访问数据库");
                System.out.println("helloworld: 访问远程redis");
                System.out.println("helloworld: 数据库持久化操作");
                Thread.sleep(20);
            } catch (BlockException e) {
                System.out.println("要访问的资源被流控了, 执行流控逻辑!");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }


//        while (true) {
//            Entry entry2 = null;
//            try {
//                // 2.1 定义资源名称
//                entry2 = SphU.entry("helloworld2");
//
//                // 2.2 执行资源逻辑代码
////                System.out.println("helloworld: 访问数据库");
//                System.out.println("helloworld: 访问远程redis");
//                System.out.println("helloworld: 数据库持久化操作");
//                Thread.sleep(20);
//            } catch (BlockException e) {
//                System.out.println("要访问的资源被流控了, 执行流控逻辑!");
//            } finally {
//                if (entry2 != null) {
//                    entry2.exit();
//                }
//            }
//        }
    }
}
