package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("test2", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() + 1);
    group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
