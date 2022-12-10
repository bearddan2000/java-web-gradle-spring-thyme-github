package example.model.schema;

import lombok.*;

import example.model.Repo;

@ToString
@Getter
@Setter
public class CsvSchema implements ISchema
{
  String name = "-";
  //String description = "-";
  // String[] keywords = new String[]{};

  @Override
  public String getX(){ return getName();}

  @Override
  public Repo getRepo()
  {
    return new Repo(name, "-", name.split("-") );
  }
}
