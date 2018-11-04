package pack.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Groups after = app.group().all();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(group.
            withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
