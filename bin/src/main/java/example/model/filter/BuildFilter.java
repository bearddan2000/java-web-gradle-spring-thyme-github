package example.model.filter;

public class BuildFilter implements IFilter, IColor
{
  String color;
  String name;
  Integer count = 0;

  public BuildFilter(String id, String title )
  {
    color = id;
    name = title;
  }

  // from  IColor
  @Override
  public String getColor() { return color; }

  public Integer getCount() { return count; }

  // from IBridgeFilter
  @Override
  public String getName(){ return name;}

  // from IFilter
  @Override
  public void increamentCount(){count++;}
}
