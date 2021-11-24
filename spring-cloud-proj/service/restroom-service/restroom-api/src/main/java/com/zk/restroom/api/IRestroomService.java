package com.zk.restroom.api;

import com.zk.restroom.pojo.Toilet;

import java.util.List;

/**
 * @author CoderZk
 */
public interface IRestroomService {

    public List<Toilet> getAvailableToilet();

    public Toilet occupy(Long id);

    public Toilet release(Long id);

    public boolean checkAvailability(Long id);
}
