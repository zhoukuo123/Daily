package com.zk.factory;

import com.zk.factory.i18n.I18N;
import com.zk.factory.i18n.I18NFactory;

public class Software {
    public static void main(String[] args) {
        I18N i18n = I18NFactory.getI18NObject("China");
        if (i18n == null) return;
        System.out.println(i18n.getTitle());
    }
}
