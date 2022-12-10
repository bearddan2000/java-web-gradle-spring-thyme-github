package example.service.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.stream.Collectors;

import example.model.Repo;
import example.model.menu.*;
import example.model.filter.*;

public class LanguageItemMenu
  implements IStrategy<LanguageMenu, LangFilter> {

    Logger logger = LoggerFactory.getLogger(LanguageItemMenu.class);

    @Override
    public Iterable<? extends IFilter> getIteratedFilter(List<IFilter> filterList, Class c){
      if (c == null) {
        logger.info("c is null");
//        System.exit(1);
      } else if (filterList == null) {
        logger.info("filterList is null");
//        System.exit(1);
      }

      List<? extends IFilter> iterFilterList = filterList.stream()
        .filter(e -> e.getClass().equals(c))
        .collect(Collectors.toList());

        if (iterFilterList == null) {
          logger.info("iterFilterList is null");
  //        System.exit(1);
        }

      Collections.sort(iterFilterList, (o1, o2) -> (o1.getName().compareTo(o2.getName())));

      return iterFilterList;
    }

    @Override
  	public LanguageMenu getIteratedFilters(BuildFilter filter, List<Repo> filteredRepoList, String name){

        List<Repo> iterRepoList = filteredRepoList.stream()
          .filter(e -> e.getBuild().getName().equals(name))
          .collect(Collectors.toList());

        LanguageMenu menu = new LanguageMenu(filter);

        menu.setCount(iterRepoList.size());

        return menu;
  	}

    @Override
  	public LanguageMenu getIteratedFilters(PlatformFilter filter, List<BuildFilter> buildList,
      List<Repo> filteredRepoList, String name){

        List<Repo> iterRepoList = filteredRepoList.stream()
          .filter(e -> e.getPlatform().getName().equals(name))
          .collect(Collectors.toList());

        LanguageMenu menu = new LanguageMenu(filter);

        menu.setCount(iterRepoList.size());

        for (BuildFilter build : buildList) {
          String buildName = build.getName();
          LanguageMenu item = getIteratedFilters(build, iterRepoList ,buildName);
          menu.getMenu().add(item);
        }


        return menu;
  	}

    @Override
  	public LanguageMenu getIteratedFilters(LangFilter filter, List<PlatformFilter> platformList,
      List<BuildFilter> buildList, List<Repo> filteredRepoList, String name){

        List<Repo> iterRepoList = filteredRepoList.stream()
          .filter(e -> e.getLang().getName().equals(name))
          .collect(Collectors.toList());

        LanguageMenu menu = new LanguageMenu(filter);

        menu.setCount(iterRepoList.size());

        for (PlatformFilter platform : platformList) {
          String platformName = platform.getName();
          LanguageMenu item = getIteratedFilters(platform, buildList, iterRepoList ,platformName);
          menu.getMenu().add(item);
        }

        return menu;
  	}

}
