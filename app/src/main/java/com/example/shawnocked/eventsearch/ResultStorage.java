package com.example.shawnocked.eventsearch;

public class ResultStorage {

    public static String venue;
    public static String performer1;
    public static String performer2;

    public ResultStorage(String venue, String performer1, String performer2){
        venue = venue;
        performer1 = performer1;
        performer2 = performer2;
    }

    public String getVenue(){
        return venue;
    }

    public String getPerformer1(){
        return performer1;
    }

    public String getPerformer2(){
        return performer2;
    }
}
