package com.zk;

/**
 * @author CoderZk
 */
//@RestController
//public class Controller implements Constant {
//    @Resource
//    private AccessLimiter accessLimiter;
//
//    @GetMapping("test")
//    public String test() {
//        accessLimiter.limitAccess("ratelimiter-test", 1);
//        return "success";
//    }
//
//    @GetMapping("test-annotation")
//    @com.zk.annotation.AccessLimiter(limit = Constant.apiCreateVmInstanceMsgQPS)
//    public String testAnnotation() {
//        String apiCreateVmInstanceMsgQPS = limitQPSConstant.getApiCreateVmInstanceMsgQPS();
//        return "success";
//    }
//
//    public int getLimitCount() {
//        return limitCount;
//    }
//
//    public void setLimitCount(int limitCount) {
//        this.limitCount = limitCount;
//    }
//
//}
