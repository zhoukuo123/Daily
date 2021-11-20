package com.zk.springcloud;

import lombok.Builder;
import lombok.Data;

/**
 * @author CoderZk
 */
@Data
@Builder
public class Product {
    private Long productId;
    private String description;
    private Long stock;

}
