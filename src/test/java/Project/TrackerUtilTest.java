package Project;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TrackerUtilTest {

    @Test
    public void testGenerateId() {
        TrackerUtil trackerUtil = new TrackerUtil();
        System.out.println(trackerUtil.generateId());
    }
}