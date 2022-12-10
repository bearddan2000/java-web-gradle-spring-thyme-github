package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.model.Repo;
import example.model.menu.*;
import example.model.filter.*;

import example.service.menu.*;

@Service
public class MenuService {

  Logger logger = LoggerFactory.getLogger(MenuService.class);

  @Autowired
  List<IFilter> filterList;

  private <T extends IMenu, U extends IFilter> List<T> getMenuItems(
    IStrategy<T, U> obj, List<Repo> repoList, Class<U> c){

    if (obj == null) {
      logger.debug("obj is null");
      System.exit(1);
    } else if (repoList == null) {
      logger.debug("repoList is null");
      System.exit(1);
    } else if (c == null) {
      logger.debug("c is null");
      System.exit(1);
    }

    List<T> menuList = new ArrayList<>();

    List<U> lst = (List<U>)obj.getIteratedFilter(filterList, c);
    List<BuildFilter> buildList = (List<BuildFilter>)obj.getIteratedFilter(filterList, BuildFilter.class);
    List<PlatformFilter> platformList = (List<PlatformFilter>)obj.getIteratedFilter(filterList, PlatformFilter.class);

    if (lst == null) {
      logger.debug("lst is null");
      System.exit(1);
    } else if (buildList == null) {
      logger.debug("buildList is null");
      System.exit(1);
    } else if (platformList == null) {
      logger.debug("platformList is null");
      System.exit(1);
    }

    for (U item : lst ) {
      String name = item.getName();
      T unit = (T)obj.getIteratedFilters(item, platformList, buildList, repoList ,name);
      menuList.add(unit);
    }

    return menuList;
  }

  public List<LanguageMenu> getLanguageMenuItems(List<Repo> repoList)
  {
    IStrategy<LanguageMenu, LangFilter> obj = new LanguageItemMenu();

    return getMenuItems(obj, repoList, LangFilter.class);
  }


  public List<TechMenu> getTechMenuItems(List<Repo> repoList)
  {
    IStrategy<TechMenu, TechFilter> obj = new TechnologyItemMenu();

    List<TechMenu> tmp = getMenuItems(obj, repoList, TechFilter.class);

    return tmp.stream()
      .filter(e -> !e.getName().equals("unclass"))
      .collect(Collectors.toList());
  }
}
