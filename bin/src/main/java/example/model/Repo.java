package example.model;

import lombok.*;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

import example.model.filter.*;
import example.model.filter.word.*;

@ToString
@Getter
@Setter
public class Repo
{
  String name;
  String description;
  LangFilter lang;
  BuildFilter build;
  PlatformFilter platform;
  TechFilter tech;
  List<KeywordsFilter> keywords;
  List<CatagoryFilter> catagories;

  public Repo(String clientName, String clientDescription, String[] clientKeywords)
  {
    name = clientName;
    description = clientDescription;
    keywords = new ArrayList<KeywordsFilter>();
    catagories = new ArrayList<CatagoryFilter>();
    lang = null;
    build = null;
    platform = null;
    tech = null;
    setKeywords(clientKeywords);
  }

  private void setKeywords(String[] value) {
    for (String word : value)
      keywords.add(new KeywordsFilter(word));
  }

  public void addCatagory(String value)
  {
    catagories.add(new CatagoryFilter(value));
  }

  public void setFilter(IFilter filter)
  {
    if(filter.getClass().equals(LangFilter.class))
      lang = (LangFilter)filter;
    else if(filter.getClass().equals(BuildFilter.class))
      build = (BuildFilter)filter;
    else if(filter.getClass().equals(PlatformFilter.class))
      platform = (PlatformFilter)filter;
    else if(filter.getClass().equals(TechFilter.class))
      tech = (TechFilter)filter;
    filter.increamentCount();
  }

  public void setDefaultFilter(List<IFilter> filterList)
  {
    IFilter filter = null;

    if (lang == null) {
      filter = getDefaultFilter(LangFilter.class, filterList);
      lang = (LangFilter)filter;
      filter.increamentCount();
    }
    if (build == null) {
      filter = getDefaultFilter(BuildFilter.class, filterList);
      build = (BuildFilter)filter;
      filter.increamentCount();
    }
    if (platform == null) {
      filter = getDefaultFilter(PlatformFilter.class, filterList);
      platform = (PlatformFilter)filter;
      filter.increamentCount();
    }
    if (tech == null) {
      filter = getDefaultFilter(TechFilter.class, filterList);
      tech = (TechFilter)filter;
      filter.increamentCount();
    }

  }

  private IFilter getDefaultFilter(Class c, List<IFilter> filterList)
  {
    return filterList.stream()
      .filter(e -> e.getName().equals("unclass"))
      .filter(e -> e.getClass().equals(c))
      .findFirst()
      .orElse(null);
  }
}
