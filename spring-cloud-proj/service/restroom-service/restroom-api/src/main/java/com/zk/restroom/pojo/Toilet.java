package com.zk.restroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CoderZk
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Toilet {
    private Long id;
    private boolean clean;
    private boolean available;
}
