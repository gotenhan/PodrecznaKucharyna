package org.ii.kucharyna.persistance;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.ii.kucharyna.persistance.communication.MongoLabCommunicator;
import org.ii.kucharyna.persistance.communication.MongoRequest;
import org.ii.kucharyna.persistance.communication.MongoRequestFactory;
import org.ii.kucharyna.persistance.converters.RecipeModelJsonConverter;
import org.ii.kucharyna.persistance.models.RecipeModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class KucharynaRepository {
  private static final String databaseName = "podreczna_kucharyna";
  private static final String recipesCollectionName = "recipes";

  private final MongoRequestFactory factory;

  private MongoLabCommunicator communicator;
  private List<RecipeModel> recipesCache;

  public KucharynaRepository(MongoLabCommunicator communicator) {
    if (communicator == null)
      throw new NullPointerException(
          "KucharynaRepository: communicator cannot be null");
    this.communicator = communicator;
    this.recipesCache = new ArrayList<RecipeModel>();
    this.factory = new MongoRequestFactory();
  }

  public KucharynaRepository(MongoLabCommunicator communicator,
      MongoRequestFactory factory) {
    if (communicator == null)
      throw new NullPointerException(
          "KucharynaRepository: communicator cannot be null");
    if (factory == null)
      throw new NullPointerException(
          "KucharynaRepository: factory cannot be null");
    this.communicator = communicator;
    this.recipesCache = new ArrayList<RecipeModel>();
    this.factory = factory;
  }

  public RecipeModel getRecipe(int index) {
    try {
      return recipesCache.get(index);
    } catch (IndexOutOfBoundsException ex) {
      StringWriter writer = new StringWriter();
      ex.printStackTrace(new PrintWriter(writer));
      getLogger().warning(writer.toString());
    }
    return null;
  }

  public List<RecipeModel> getRecipes() {
    return recipesCache;
  }

  public List<RecipeModel> getRecipes(int from, int to) {
    try {
      return recipesCache.subList(from, to);
    } catch (IndexOutOfBoundsException ex) {
      logException(ex);
    }
    return null;
  }

  public List<RecipeModel> fillRecipes() throws IOException, URISyntaxException {
    recipesCache.clear();
    MongoRequest request = factory.createGetAllDocumentsRequest(
        KucharynaRepository.databaseName,
        KucharynaRepository.recipesCollectionName);
    String response = communicator.sendReceive(request);
    try {
      recipesCache = RecipeModelJsonConverter.fromJsonArray(response);
    } catch (JsonParseException ex) {
      logException(ex);
    }
    return recipesCache;
  }

  protected void logException(Exception ex) {
    StringWriter writer = new StringWriter();
    ex.printStackTrace(new PrintWriter(writer));
    getLogger().warning(writer.toString());
  }

  protected Logger getLogger() {
    return Logger.getLogger("KucharynaRepository");
  }

  public int addRecipe(RecipeModel model) {
    // TODO check if null
    return recipesCache.add(model) ? recipesCache.size() - 1 : -1; 
  }

  public void update(RecipeModel model, int modelNr) {
    // TODO check if null
    recipesCache.set(modelNr, model);
  }

}
