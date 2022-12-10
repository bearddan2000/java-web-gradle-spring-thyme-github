package example.service.menu;

import java.util.*;

import example.model.Repo;
import example.model.menu.*;
import example.model.filter.*;

public interface IStrategy<T extends IMenu, U extends IFilter> {

  Iterable<? extends IFilter> getIteratedFilter(List<IFilter> filterList, Class c);

  T getIteratedFilters(BuildFilter filter, List<Repo> filteredRepoList, String name);

  T getIteratedFilters(PlatformFilter filter, List<BuildFilter> buildList, List<Repo> filteredRepoList, String name);

  T getIteratedFilters(U filter, List<PlatformFilter> platformList, List<BuildFilter> buildList, List<Repo> filteredRepoList, String name);

}
