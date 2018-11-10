package pack.pdt.addressbook.tests;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header1").withFooter("footer1")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);

    assertEquals(app.group().count(), before.size() + 1);
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.
            withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
