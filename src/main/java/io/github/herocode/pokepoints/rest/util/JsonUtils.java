/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.rest.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author kieckegard
 */
public class JsonUtils
{
    private static String readUrl(String urlString) throws MalformedURLException, FileNotFoundException, IOException{
        BufferedReader bufferedReader;
        
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        
        StringBuilder stringBuilder = new StringBuilder();
        
        int read;
        char[] chars = new char[1024];
        while((read = bufferedReader.read(chars)) != -1)
            stringBuilder.append(chars, 0, read);
        
        return stringBuilder.toString();
    }
    
    public static JsonObject getJsonFromUrl(String url) throws MalformedURLException, IOException{
        String json = readUrl(url);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        return jsonObject;
    }
}
