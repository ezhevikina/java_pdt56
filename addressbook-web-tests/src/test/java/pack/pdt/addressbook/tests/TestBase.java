package pack.pdt.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pack.pdt.addressbook.appmanager.ApplicationHelper;

public class TestBase {

  protected final ApplicationHelper app = new ApplicationHelper();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
