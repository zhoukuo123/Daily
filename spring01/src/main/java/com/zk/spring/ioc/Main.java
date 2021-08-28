package com.zk.spring.ioc;

import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * @author CoderZk
 */
public class Main {
    public static void main(String[] args) {
        //字符串
        String key="API_CREATE_VM_INSTANCE_FROM_VOLUME_MSG_TB_MAX_FLOW_RATE";
        //小写个数
        int lower=0;
        //大写个数
        int capit=0;
        //混合中，大写开始的下标
        int blend=0;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(Character.isLowerCase(c)){//小写
                lower=lower+1;//小写个数+1
            }else {
                capit=capit+1; //大写个数+1
                if(capit==1) {
                    blend=i;
                }
            }
        }
        if(lower<key.length()&&capit<key.length()){ //大写和小写的个数都小于总数，说明既有大写又有小写
            key=key.substring(0,blend)+"_"+key.substring(blend);
        }
        System.out.println(key.toUpperCase());
    }
}
