package com.zk.springcloud;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author CoderZk
 */
@Data
public class Product implements Serializable {

    private String name;
    private BigDecimal price;

}
