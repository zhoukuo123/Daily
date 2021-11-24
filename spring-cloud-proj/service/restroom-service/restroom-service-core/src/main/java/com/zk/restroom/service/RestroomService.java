package com.zk.restroom.service;

import com.zk.restroom.api.IRestroomService;
import com.zk.restroom.converter.ToiletConverter;
import com.zk.restroom.dao.ToiletDao;
import com.zk.restroom.entity.ToiletEntity;
import com.zk.restroom.pojo.Toilet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoderZk
 */
@Slf4j
@RestController
@RequestMapping("toilet-service")
public class RestroomService implements IRestroomService {

    @Autowired
    private ToiletDao toiletDao;

    @Override
    @GetMapping("/checkAvailable")
    public List<Toilet> getAvailableToilet() {
        List<ToiletEntity> result = toiletDao.findAllByCleanAndAvailable(true, true);
        return result.stream()
                .map(ToiletConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @PostMapping("/occupy")
    public Toilet occupy(Long id) {
        ToiletEntity toilet = toiletDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Toilet not found"));

        if (!toilet.isAvailable() || !toilet.isClean()) {
            throw new RuntimeException("restroom not available or unclean");
        }

        toilet.setAvailable(false);
        toilet.setClean(false);
        toiletDao.save(toilet);

        return ToiletConverter.convert(toilet);
    }

    @Override
    @PostMapping("/release")
    public Toilet release(Long id) {
        ToiletEntity toilet = toiletDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Toilet not found"));

        toilet.setClean(true);
        toilet.setAvailable(true);
        toiletDao.save(toilet);

        return ToiletConverter.convert(toilet);
    }

    @Override
    @GetMapping("/checkAvailability")
    public boolean checkAvailability(Long id) {
        ToiletEntity toilet = toiletDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Toilet not found"));
        return toilet.isAvailable();
    }
}
