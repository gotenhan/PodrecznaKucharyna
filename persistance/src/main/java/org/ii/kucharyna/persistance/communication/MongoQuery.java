package org.ii.kucharyna.persistance.communication;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

class OperationNotAllowedException extends Exception{
  private static final long serialVersionUID = 516518059784170472L;

  public OperationNotAllowedException(String string) {
    super(string);
  }
}

public class MongoQuery {
  private String jsonQuery;
  private boolean returnCount = false;
  private Integer skip;
  private Integer limit;
  private LinkedHashMap<String, Integer> fields;
  private String jsonFields;
  private boolean findOne = false;
  private LinkedHashMap<String, Integer> sortBy;
  private String jsonSortBy;
  
  public MongoQuery(){
  }
  
  public MongoQuery findOne(){
    this.findOne = Boolean.valueOf(true);
    return this;
  }
  
  public MongoQuery limit(int limit){
    this.limit = Integer.valueOf(limit);
    return this;
  }
  
  public MongoQuery skip(int skip){
    this.skip = Integer.valueOf(skip);
    return this;
  }
  
  public MongoQuery returnCount(){
    this.returnCount = Boolean.valueOf(true);
    return this;
  }
  
  public MongoQuery fields(Map<String, Integer> fields) throws OperationNotAllowedException {
    if(fields == null)
      throw new NullPointerException();
    if(this.fields != null)
      throw new OperationNotAllowedException("You cannot use two methods for specifying fields");
    if(fields.isEmpty())
      throw new IllegalArgumentException("fields collection cannot be empty");
    this.fields = new LinkedHashMap<String, Integer>();
    this.fields.putAll(fields);
    return this;
  }
  
  public MongoQuery fields(String fields) throws OperationNotAllowedException{
    if(fields == null)
      throw new NullPointerException("fields cannot be null");
    if(this.fields != null)
      throw new OperationNotAllowedException("You cannot use two methods for specifying fields");
    this.jsonFields = fields;
    return this;
  }
  
  public MongoQuery sortBy(Map<String, Integer> sortBy) throws OperationNotAllowedException{
    if(sortBy == null)
      throw new NullPointerException();
    if(sortBy.isEmpty())
      throw new IllegalArgumentException("sortBy collection cannot be empty");
    if(this.jsonSortBy != null)
      throw new OperationNotAllowedException("You cannot use two sort methods");
    this.sortBy = new LinkedHashMap<String, Integer>();
    this.sortBy.putAll(sortBy);
    return this;
  }

  public MongoQuery sortBy(String sortBy) throws OperationNotAllowedException{
    if(sortBy == null)
      throw new NullPointerException("sortBy cannot be null");
    if(this.sortBy != null)
      throw new OperationNotAllowedException("You cannot use two sort methods");
    this.jsonSortBy = sortBy;
    return this;
  }

  public MongoQuery jsonQuery(String query){
    if(query == null)
      throw new NullPointerException("query cannot be null");
    this.jsonQuery = query;
    return this;
  }

  public List<NameValuePair> toRestParams() throws IOException{
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    if(jsonQuery != null)
      params.add(new BasicNameValuePair("q", jsonQuery));
    if(returnCount)
      params.add(new BasicNameValuePair("c","true"));
    if(findOne)
      params.add(new BasicNameValuePair("fo","true"));
    if(skip != null)
      params.add(new BasicNameValuePair("sk", skip.toString()));
    if(limit != null)
      params.add(new BasicNameValuePair("l", limit.toString()));
    if(jsonFields != null)
      params.add(new BasicNameValuePair("f", jsonFields));
    if(fields != null){
      String fieldsString = mapToJson(fields);
      params.add(new BasicNameValuePair("f", fieldsString));
    }
    if(jsonSortBy != null)
      params.add(new BasicNameValuePair("s", jsonSortBy));
    if(sortBy != null){
      String sortByString = mapToJson(sortBy);
      params.add(new BasicNameValuePair("s", sortByString));
    }
    
    return params;
  }

  private String mapToJson(LinkedHashMap<String, Integer> fields) throws IOException{
    StringWriter writer = new StringWriter();
    JsonFactory factory = new JsonFactory();
    JsonGenerator generator = factory.createJsonGenerator(writer);
    generator.writeStartObject();
    for(Map.Entry<String, Integer> entry:fields.entrySet()){
      generator.writeNumberField(entry.getKey(), entry.getValue());
    }
    generator.writeEndObject();
    generator.close();
    String json = writer.toString();
    writer.close();
    return json;
  }
  
}
