package org.techtown.se_project_manager.helper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Text;

public class TextHelperTest {
    private TextHelper helper = null;

    @Before
    public void setUp() throws Exception {
        helper = TextHelper.getInstance();
    }
    @After
    public void tearDown() throws Exception {
        helper = null;
    }
    @Test
    public void testGetText() {
            Assert.assertEquals(helper.getText(49), "low");
    }
 }
