package example.model.menu;

import lombok.*;
import java.util.*;

import example.model.filter.IColor;

@ToString
@Getter
@Setter
public class LanguageMenu implements IMenu<LanguageMenu>
{
  String color;
  String name;
  Integer count = 0;
  List<LanguageMenu> menu;

  public LanguageMenu(IColor filter)
  {
    color = filter.getColor();
    name = filter.getName();
    menu = new ArrayList();
  }

  @Override
  public void setCount(Integer i){count = i;}

  @Override
  public List<LanguageMenu> getMenu(){return menu;}
}
