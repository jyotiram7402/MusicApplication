package org.gfg.musicapplication.model;

public enum FeedbackRating {

    BAD(1, "bad"),
    AVERAGE(2, "average"),
    EXCELLENT(3, "excellent");
    Integer id;
    String rating;

    FeedbackRating(Integer id,String rating){
        this.id =id ;
        this.rating = rating;
    }

}
