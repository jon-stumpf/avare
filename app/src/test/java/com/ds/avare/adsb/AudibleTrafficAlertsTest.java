package com.ds.avare.adsb;

import org.junit.Assert;
import org.junit.Test;

public class AudibleTrafficAlertsTest {

    @Test
    public void angleFromCoordinate_strightUp() {
        Assert.assertEquals(0, AudibleTrafficAlerts.angleFromCoordinate(0, 0, 90, 0), 0);
    }

    @Test
    public void angleFromCoordinate_east() {
        Assert.assertEquals(90, AudibleTrafficAlerts.angleFromCoordinate(0, 0, 0, 45), 0);
    }

    @Test
    public void angleFromCoordinate_west() {
        Assert.assertEquals(270, AudibleTrafficAlerts.angleFromCoordinate(0, 90, 0, 45), 0);
    }

    @Test
    public void angleFromCoordinate_diaganal() {
        Assert.assertEquals(225, AudibleTrafficAlerts.angleFromCoordinate(43.5439, -96.730, 42.57, -98.0421), 0.5);
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_east() {
        Assert.assertEquals(3,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        0, 0, 0, 45, 0));
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_nearEnoughToEastRounded() {
        Assert.assertEquals(3,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        0, 0, 0, 45, 5));
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_straightBehindFromAnAngle() {
        Assert.assertEquals(6,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        43.5439, -96.730, 42.57, -98.0421, 44.999));
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_oneMinuteToMidnightRounded() {
        Assert.assertEquals(12,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        43.5439, -96.730, 44.8402, -96.7621, 0));
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_oneMinutePastMidnightRounded() {
        Assert.assertEquals(12,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        43.5439, -96.730, 48.034, -96.654, 0));
    }

    @Test
    public void nearestClockHourFromHeadingAndLocations_nearlySouthAndIFacingSouth() {
        Assert.assertEquals(12,
                AudibleTrafficAlerts.nearestClockHourFromHeadingAndLocations(
                        43.5439, -96.730, 39.5718, -96.735, 180));
    }

    @Test
    public void closestApproachTime_eastWestHeadOn() {
        final double lat1 = 45, lat2 = 45, lon1 = -95, lon2 = -94;
        final int velo1 = 60, velo2 = 60;
        final float heading1 = 270, heading2 = 90;
        final double caTime = AudibleTrafficAlerts.closestApproachTime(lat1, lon1, lat2, lon2, heading1, heading2, velo1, velo2);
        Assert.assertEquals("Closest approach seconds", .25, caTime, .5);
    }

    @Test
    public void closestApproachTime_northSouthHeadOn() {
        final double lat1 = 45, lat2 = 46, lon1 = -95, lon2 = -95;
        final int velo1 = 60, velo2 = 60;
        final float heading1 = 180, heading2 = 0;
        final double caTime = AudibleTrafficAlerts.closestApproachTime(lat1, lon1, lat2, lon2, heading1, heading2, velo1, velo2);
        Assert.assertEquals("Closest approach seconds", .25, caTime, .1);
    }
}