package com.zk.factory.i18n;

public class I18NFactory {
    // 静态工厂
    public static I18N getI18NObject(String area) {
        if (area.equals("China")) {
            return new Chinese();
        } else if (area.equals("Spain")) {
            return new Spanish();
        } else if (area.equals("Italy")) {
            return new Italian();
        } else {
            return null;
        }
    }
}
