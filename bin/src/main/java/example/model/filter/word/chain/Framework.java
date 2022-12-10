package example.model.filter.word.chain;

import java.util.*;

import example.model.Repo;

public class Framework implements IChain {

  IChain next = null;

  List<String> list = new ArrayList<String>()
  {{
    add("springframework");
  }};

  @Override
  public void eval(String word, Repo repo)
  {
    if (list.contains(word)) {
      repo.addCatagory(this.getClass().getSimpleName());
    }
    else if (next != null) {
      next.eval(word, repo);
    }
  }
}
