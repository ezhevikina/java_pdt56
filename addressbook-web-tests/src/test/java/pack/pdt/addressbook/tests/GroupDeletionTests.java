package pack.pdt.addressbook.tests;

import org.testng.annotations.*;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);

    assertEquals(app.group().count(),before.size() -1);
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
