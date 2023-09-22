package com.yc;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
@ActiveProfiles("localinit")
//@ActiveProfiles("dockerinit")

public class TestLocalDbInit {
    @Autowired
    private DataSource ds;
    @Test
    public void testLocalDbInit() {
        Assert.assertNotNull(ds);
        System.out.println(TestLocalDbInit.class.getResource("/"));
        System.out.println(ds);
    }
}
