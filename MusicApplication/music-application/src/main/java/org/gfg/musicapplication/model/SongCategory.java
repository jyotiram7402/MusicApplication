package org.gfg.musicapplication.model;

public enum SongCategory {
    HIPHOP(0,"hhiphop"),
    INTRUMENTAL(1, "instrumental"),
    JAZZ(2,"jazz"),
    CLASSICAL(3, "classical"),
    FOLK(4, "folk"),
    ROMANTIC(5, "romatic");

    Integer id;
    String category;

    SongCategory(Integer id,String category){
        this.id =id ;
        this.category = category;
    }


}
