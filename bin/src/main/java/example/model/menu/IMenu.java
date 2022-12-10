package example.model.menu;

import java.util.List;

public interface IMenu<T extends IMenu>
{
  void setCount(Integer i);

  List<T> getMenu();
}
