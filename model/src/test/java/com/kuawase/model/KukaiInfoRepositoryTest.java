package com.kuawase.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class KukaiInfoRepositoryTest {
    private final String kukaiName = "テスト句会";
    private final Calendar calendar = Calendar.getInstance();
    private Date startDate;
    private Date endDate;
    private final String haiku = "古池や蛙飛び込む水の音";
    private final String author = "松尾芭蕉";
    private final int point = 10;

    @Before
    public void setUp() {
        calendar.set(2000, 1, 1);
        startDate = calendar.getTime();
        calendar.set(2010, 12, 31);
        endDate = calendar.getTime();
    }

    @Test
    public void getInstance() {
        KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void createAndGetKukaiInfo() {
        KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();
        dataSource.createKukaiInfo(kukaiName, startDate, endDate);
        KukaiInfo kukaiInfo = dataSource.getKukaiInfo();
        Objects.requireNonNull(kukaiInfo);
    }

    @Test
    public void addHaikuInfoToKukaiInfo() {
        KukaiInfoDataSource model = KukaiInfoRepository.getInstance();
        model.createKukaiInfo(kukaiName, startDate, endDate);
        KukaiInfo kukaiInfo = model.getKukaiInfo();
        Objects.requireNonNull(kukaiInfo);
        Assert.assertEquals(kukaiName, kukaiInfo.getName());
        Assert.assertEquals(startDate, kukaiInfo.getStartDate());
        Assert.assertEquals(endDate, kukaiInfo.getEndDate());
    }
}