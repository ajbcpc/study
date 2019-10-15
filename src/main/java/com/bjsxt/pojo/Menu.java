package com.bjsxt.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private int id;
    private String text;
    private int pid;
    private String filename;
    private Attributes attributes;
    private List<Menu> children;
}
