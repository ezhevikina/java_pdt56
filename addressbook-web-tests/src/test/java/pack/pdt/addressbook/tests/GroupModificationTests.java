package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().exists()) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test3").withHeader("test8").withFooter("test2");
    app.group().modify(group);

    assertEquals(app.group().count(), before.size());
    Groups after = app.group().all();
    assertThat(before, equalTo(after.without(modifiedGroup).withAdded(group)));
  }
}
