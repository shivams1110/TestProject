package com.royalways.testproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Intel on 28-12-2017.
 */

public class Post {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("body")
    @Expose
    String body;


    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
