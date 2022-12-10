package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import example.model.Repo;
import example.model.filter.*;
import example.model.filter.word.*;

@Service
public class FilterService {
  @Autowired
  List<IFilter> filterList;

  public Iterable<? extends IFilter> getIteratedFilter(Class c){

    List<? extends IFilter> iterFilterList = filterList.stream()
      .filter(e -> e.getClass().equals(c))
      .collect(Collectors.toList());

    Collections.sort(iterFilterList, (o1, o2) -> (o1.getName().compareTo(o2.getName())));

    return iterFilterList;
  }

  public Iterable<KeywordsFilter> getIteratedFilter(List<Repo> repoList)
  {
    List<KeywordsFilter> keywordsFilterList = new ArrayList<KeywordsFilter>();

    HashMap<String, KeywordsFilter> map = new HashMap<String, KeywordsFilter>();

    for (Repo repo : repoList )
    { // start repo loop

      for (KeywordsFilter filter : repo.getKeywords() )
      { // start filter loop

        String name = filter.getName().trim().toLowerCase();

        if (!name.isEmpty() && name != null && !name.equals("") )
        { // start name condition

          KeywordsFilter item;

          if(map.containsKey(name))
            item = map.get(name);
          else
            item = new KeywordsFilter(name);

          item.increamentCount();

          map.put(name, item);

        } // end name condition

      } // end filter loop

    } // end repo loop

    keywordsFilterList = map.values()
      .stream()
      .sorted(Comparator.comparing(KeywordsFilter::getName))
      .collect(Collectors.toList());

    List<String> filterNames = filterList.stream()
      .map(IFilter::getName)
      .collect(Collectors.toList());

    return keywordsFilterList.stream()
      .filter(e -> !filterNames.contains(e.getName()))
      .collect(Collectors.toList());
  }
}
