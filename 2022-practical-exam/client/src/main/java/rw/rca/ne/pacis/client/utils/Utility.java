package rw.rca.ne.pacis.client.utils;

import org.springframework.beans.factory.annotation.Value;

public class Utility {

    private static String backendUrl = "http://localhost:8000";

    public static String formatURL(String url){
        return backendUrl + url;
    }
}
