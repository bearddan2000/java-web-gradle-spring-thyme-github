package example.model.filter.word.chain;

import example.model.Repo;

interface IChain {
  void eval(String word, Repo repo);
}
