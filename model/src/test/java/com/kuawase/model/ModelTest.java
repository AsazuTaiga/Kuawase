package com.kuawase.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ModelTest {
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
        ModelInterface model = Model.getInstance();
        Assert.assertNotNull(model);
    }

    @Test
    public void createAndGetKukaiInfo() {
        ModelInterface model = Model.getInstance();
        model.createKukaiInfo(kukaiName, startDate, endDate);
        KukaiInfo kukaiInfo = model.getKukaiInfo();
        Assert.assertEquals(kukaiName, kukaiInfo.getName());
        Assert.assertEquals(startDate, kukaiInfo.getStartDate());
        Assert.assertEquals(endDate, kukaiInfo.getEndDate());
    }

    @Test
    public void addHaikuInfoToKukaiInfo() {
        ModelInterface model = Model.getInstance();
        model.createKukaiInfo(kukaiName, startDate, endDate);
        KukaiInfo kukaiInfo = model.getKukaiInfo();

        HaikuInfo haikuInfo = new HaikuInfo(haiku, author);
        kukaiInfo.getHaikuInfos().add(haikuInfo);
        kukaiInfo.getHaikuInfos().get(0).setPoint(point);
        Assert.assertEquals(point, kukaiInfo.getHaikuInfos().get(0).getPoint());
    }
}