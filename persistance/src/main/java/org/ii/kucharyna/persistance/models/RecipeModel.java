package org.ii.kucharyna.persistance.models;

import java.util.List;


public class RecipeModel {

  private ObjectId _id = null;
  private String description = null;
  private String name = null;
  private String instructions = null;
  private List<String> tags = null;


  public RecipeModel() {
  }

  public void addTag(String tag) {
    if (tag != null)
      tags.add(tag);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RecipeModel other = (RecipeModel) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (instructions == null) {
      if (other.instructions != null)
        return false;
    } else if (!instructions.equals(other.instructions))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (tags == null) {
      if (other.tags != null)
        return false;
    } else if (!tags.equals(other.tags))
      return false;
    return true;
  }

  public ObjectId get_id() {
    return _id;
  }

  public String getDescription() {
    return description;
  }

  public String getInstructions() {
    return instructions;
  }

  public String getName() {
    return name;
  }

  public List<String> getTags() {
    return tags;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((description == null) ? 0 : description.hashCode());
    result = prime * result
        + ((instructions == null) ? 0 : instructions.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((tags == null) ? 0 : tags.hashCode());
    return result;
  }

  public void removeTag(String tag) {
    if (tag != null)
      tags.remove(tag);
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RecipeModel [");
    if (_id != null)
      builder.append("_id=").append(_id).append(", ");
    if (description != null)
      builder.append("description=").append(description).append(", ");
    if (name != null)
      builder.append("name=").append(name).append(", ");
    if (instructions != null)
      builder.append("instructions=").append(instructions).append(", ");
    if (tags != null)
      builder.append("tags=").append(tags);
    builder.append("]");
    return builder.toString();
  }
  
  public static class ObjectId{
    private String $oid;
    public ObjectId(){
      $oid = Integer.toString(new Object().hashCode());
    }
    public String get$oid() {
      return $oid;
    }
    public void set$oid(String $oid) {
      this.$oid = $oid;
    }
  }
}
