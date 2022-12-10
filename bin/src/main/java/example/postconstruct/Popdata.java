package example.postconstruct;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import example.model.*;
import example.model.filter.*;

public class Popdata {

  List<example.model.schema.CsvSchema> csvSchemaList = new ArrayList();

  List<example.model.schema.ApiSchema> apiSchemaList = new ArrayList();

  public List<Repo> repositoryList(List<IFilter> filterList)
  {

    Filter lst = null;

    List<Repo> repoList = new ArrayList();

    if(csvSchemaList.isEmpty())
      lst = new Filter.Builder()
                  .setFilter(filterList)
                  .setSchema(apiSchemaList)
                  .build();
    else
      lst = new Filter.Builder()
                  .setFilter(filterList)
                  .setSchema(csvSchemaList)
                  .build();

    if(lst != null)
    {
        repoList = lst.iterateList();
    }

    return repoList;
  }

  public void readPortalFile()
  {
    String PWD = System.getenv("PWD");

    try {
        // set correct path to csv file on your disc
        File csvFile = new File(PWD + "/src/main/resources/download.csv");

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(example.model.schema.CsvSchema.class)
//                .withHeader()
                .withColumnSeparator(',');
//                .withComments();

        MappingIterator<example.model.schema.CsvSchema> complexUsersIter = csvMapper
                .readerWithTypedSchemaFor(example.model.schema.CsvSchema.class)
                .with(csvSchema)
                .readValues(csvFile);

        csvSchemaList = complexUsersIter.readAll();
    }
    catch (IOException e) {
      System.out.println("Missing File");
    }
  }
}
