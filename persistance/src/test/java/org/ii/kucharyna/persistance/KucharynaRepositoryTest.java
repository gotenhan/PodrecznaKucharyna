package org.ii.kucharyna.persistance;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.ii.kucharyna.persistance.communication.MongoLabCommunicator;
import org.ii.kucharyna.persistance.communication.MongoRequestFactory;
import org.ii.kucharyna.persistance.converters.RecipeModelJsonConverter;
import org.ii.kucharyna.persistance.models.RecipeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;
import com.google.mockwebserver.RecordedRequest;

public class KucharynaRepositoryTest {

  private MockWebServer server;
  private MongoRequestFactory factory;
  private MongoLabCommunicator communicator;
  private ArrayList<RecipeModel> expectedRecipes;

  @Before
  public void setUp() throws Exception {
    expectedRecipes = new ArrayList<RecipeModel>();

    RecipeModel recipeStub1 = createRecipeStub("recipe1");
    RecipeModel recipeStub2 = createRecipeStub("recipe2");
    expectedRecipes.add(recipeStub1);
    expectedRecipes.add(recipeStub2);

    String recipesJson = RecipeModelJsonConverter.toJson(expectedRecipes);

    //System.setProperty("javax.net.ssl.trustStoreType", "jks");
    //System.setProperty("javax.net.ssl.trustStore", "localhost.keystore");
    System.setProperty("javax.net.debug", "ssl");
    //System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    server = new MockWebServer();
    server.useHttps(HttpsURLConnection.getDefaultSSLSocketFactory(), false);
    server.enqueue(new MockResponse().setBody(recipesJson));
    server.play();
    factory = new MongoRequestFactory(server.getHostName(), "",
        server.getPort(), "testToken");
    communicator = new MongoLabCommunicator();
  }

  @Test
  public void fillRecipesTest() throws IOException, URISyntaxException,
      InterruptedException {
    KucharynaRepository repository = new KucharynaRepository(communicator,
        factory);
    List<RecipeModel> recipes = repository.fillRecipes();

    Assert.assertEquals(2, recipes.size());
    Assert.assertTrue(recipes.containsAll(expectedRecipes));
    RecordedRequest request = server.takeRequest();
    Assert
        .assertEquals(
            "/databases/podreczna_kucharyna/collections/recipes?apiToken=testToken",
            request.getPath());
  }

  protected RecipeModel createRecipeStub(String name) {
    RecipeModel recipe = new RecipeModel();
    recipe.setDescription("test description");
    recipe.setInstructions("test instructions");
    recipe.setName(name);
    List<String> tags = Arrays.asList(new String[] { "tag1", "tag2", "tag3" });
    recipe.setTags(tags);
    return recipe;
  }

}
