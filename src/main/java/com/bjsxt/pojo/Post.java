package com.bjsxt.pojo;

import lombok.Data;

@Data
public class Post {

    private int id;
    private String title;
    private String content;
    private String content_abb;
    private int likes;
    private int collection;
}
