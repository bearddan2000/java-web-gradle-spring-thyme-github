package example.model.menu;

import lombok.*;
import java.util.*;

import example.model.filter.IColor;

@ToString
@Getter
@Setter
public class TechMenu implements IMenu<TechMenu>
{
  String color;
  String name;
  Integer count = 0;
  List<TechMenu> menu;

  public TechMenu(IColor filter)
  {
    color = filter.getColor();
    name = filter.getName();
    menu = new ArrayList();
  }

  @Override
  public void setCount(Integer i){count = i;}

  @Override
  public List<TechMenu> getMenu(){return menu;}

}
