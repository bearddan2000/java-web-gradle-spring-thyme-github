package example.model.filter.word;

import example.model.filter.IFilter;

public class CatagoryFilter implements IFilter
{
  String name;
  Integer count = 0;

  public CatagoryFilter(String title )
  {
    name = title;
  }

  public Integer getCount() { return count; }

  // from IBridgeFilter
  @Override
  public String getName(){ return name;}

  // from IFilter
  @Override
  public void increamentCount(){count++;}
}
