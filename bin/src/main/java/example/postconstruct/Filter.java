package example.postconstruct;

import java.util.*;

import example.model.Repo;
import example.model.filter.*;
import example.model.schema.*;


public class Filter {

  public static class Builder {

      List<IFilter> filterList = null;

      List<ISchema> schemaList = null;

      public Builder setFilter(List<IFilter> lst){
        filterList = lst;
        return this;
      }

      public Builder setSchema(List<? extends ISchema> lst){
        schemaList = (List<ISchema>)lst;
        return this;
      }

      public Filter build()
      {
        if (filterList != null && schemaList != null)
          return new Filter(this);

        System.out.println("[ERROR] Filter properties not set");

        return null;
      }
  }

  List<IFilter> filterList = null;

  List<ISchema> schemaList = null;

  List<Repo> repoList = new ArrayList<Repo>();

  private Filter(Builder builder)
  {
    filterList = builder.filterList;
    schemaList = builder.schemaList;
  }

  public List<Repo> iterateList()
  {

    for (ISchema schema : schemaList)
    { // begin schema loop

      Repo repo = schema.getRepo();

      // System.out.println("[Info: ]" + repo.toString());

      String[] repoKeywords = schema.getX().split("-");

      for (IFilter filter : filterList)
      { // begin filter loop

        // my github profile should not be cataloged
        if (repoKeywords.length > 1)
        { // begin keyword condtion

          // some projects may have more than 1 keyword
          for (String word : repoKeywords)
          { // begin word loop

            if(filter.getName().equals(word))
            { // begin catalog word condtion

              repo.setFilter(filter);

            } // end filter name condtion

          } // end word loop

        } // end word condtion

      } // end filter loop

      // deefaultFilter assignment
      repo.setDefaultFilter(filterList);

      repoList.add(repo);

    } // end schema loop

    return repoList;
  }
}
