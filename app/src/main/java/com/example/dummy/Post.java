package com.example.dummy;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Post{

	@SerializedName("Post")
	private List<PostItem> post;

	public List<PostItem> getPost(){
		return post;
	}
}