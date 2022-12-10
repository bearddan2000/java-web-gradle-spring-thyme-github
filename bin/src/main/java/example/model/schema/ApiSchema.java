package example.model.schema;

import lombok.*;

import example.model.Repo;

@Getter
@Setter
public class ApiSchema implements ISchema
{

  String name;

  String description;

  String[] keywords;
  
  @Override
  public String getX(){ return getName();}

  @Override
  public Repo getRepo()
  {
    return new Repo(name, description, keywords );
  }
}
