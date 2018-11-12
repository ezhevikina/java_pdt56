package ru.stqa.pft.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    BBHLocation geoIP = new GeoIP().getGeoIPSoap12().getUserLocation("72.229.28.185");
    assertEquals(geoIP.getCountryCode(), "US");
  }

  @Test(enabled = false)
  public void testInvalidIp(){
    BBHLocation geoIP = new GeoIP().getGeoIPSoap12().getUserLocation("195.7.20.xx");
    assertEquals(geoIP.getCountryCode(), "");
  }
}
