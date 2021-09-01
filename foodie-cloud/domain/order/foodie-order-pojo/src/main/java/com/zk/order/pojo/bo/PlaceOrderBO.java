package com.zk.order.pojo.bo;

import com.zk.pojo.ShopcartBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author CoderZk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderBO {
    private SubmitOrderBO order;

    private List<ShopcartBO> items;
}
