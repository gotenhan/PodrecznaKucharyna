package org.ii.kucharyna.persistance.models;

import java.util.ArrayList;

import de.undercouch.bson4jackson.types.ObjectId;

public class RecipeModel { 

	private ObjectId _id = null;
	private String description = null;
	private String name = null;
	private String instructions = null;
	private ArrayList<String> tags = new ArrayList<String>();

	// private IngredientModel[] ingredients;

	public RecipeModel() {
	}

	public void addTag(String tag) {
		if (tag != null)
			tags.add(tag);
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

	public ArrayList<String> getTags() {
		return tags;
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

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
