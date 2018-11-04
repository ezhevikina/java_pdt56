package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pack.pdt.addressbook.model.GroupData;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().exists()) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(deletedGroup);
    Assert.assertEquals(before,after);
  }
}
