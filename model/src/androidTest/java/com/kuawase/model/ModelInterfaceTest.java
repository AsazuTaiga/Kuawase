package com.kuawase.model;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ModelInterfaceTest {

    private Instrumentation instrumentation;
    private Context context;
    private ModelInterface model;
    private String haikuExample = "古池や蛙飛び込む水の音";
    private String authorExample = "松尾芭蕉";
    private String kukaiNameExample = "ホトトギス句会";
    private Date startDateExmaple = new Date();
    private Date endDateExample = new Date();

    @Before
    public void setUp() throws Exception {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        context = instrumentation.getContext();
        model = ModelImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstanceTest() {
        Assert.assertNotNull(model);
    }

    @Test
    public void crateHaikuTest() {
        Haiku haiku = model.createHaiku(haikuExample, authorExample);
        Assert.assertEquals(haikuExample, haiku.getHaiku());
        Assert.assertEquals(authorExample, haiku.getAuthor());
        model.setHaikuPoint(haiku, 100);
        Assert.assertEquals(100, haiku.getPoint());
    }

    @Test
    public void createKukaiInfoTest() {
        KukaiInfo kukaiInfo = model.createKukaiInfo(kukaiNameExample, startDateExmaple, endDateExample);
        Assert.assertEquals(kukaiNameExample, kukaiInfo.getName());
        Assert.assertEquals(startDateExmaple, kukaiInfo.getStartDate());
        Assert.assertEquals(endDateExample, kukaiInfo.getEndDate());
        Assert.assertNotNull(kukaiInfo.getUuid());
    }

    @Test
    public void saveKukaiInfoTest() {
        KukaiInfo kukaiInfo = model.createKukaiInfo(kukaiNameExample, startDateExmaple, endDateExample);
        model.saveKukaiInfo(context, kukaiInfo);
        List<KukaiInfo> kukaiInfos = model.getAllKukaiInfo(context);
        Assert.assertEquals(kukaiInfos.get(0).getName(), kukaiNameExample);
    }
}