package pack.pdt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pack.pdt.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  //Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }
/*
  @BeforeMethod(enabled = false)
  public void logTestStart(Method method, Object[] parameters) {
    logger.info("Start test " + method.getName()
            + " with parameters" + Arrays.asList(parameters));
  }

  @AfterMethod(enabled = false)
  public void logTestStop(Method method) {
    logger.info("Stop test " + method.getName());
  }
*/
}
