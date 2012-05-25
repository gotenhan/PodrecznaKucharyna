package org.ii.kucharyna.persistance.converters;

import java.io.IOException;
import java.util.List;

import org.ii.kucharyna.persistance.models.RecipeModel;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecipeModelJsonConverter {
  public static String toJson(RecipeModel recipe)
      throws JsonGenerationException, JsonMappingException, IOException {
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    return mapper.writerWithType(RecipeModel.class).writeValueAsString(recipe);
  }

  public static RecipeModel fromJson(String json) throws JsonParseException,
      JsonMappingException, IOException {
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    return mapper.readValue(json, RecipeModel.class);
  }

  public static String toJson(List<RecipeModel> recipes)
      throws JsonGenerationException, JsonMappingException, IOException {
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    return mapper.writerWithType(new TypeReference<List<RecipeModel>>() {
    }).writeValueAsString(recipes);
  }

  public static List<RecipeModel> fromJsonArray(String jsonArray) throws JsonParseException, JsonMappingException, IOException {
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    List<RecipeModel> recipesCache = null;
    recipesCache = mapper.readValue(jsonArray,
        new TypeReference<List<RecipeModel>>() {
        });

    return recipesCache;
  }
}