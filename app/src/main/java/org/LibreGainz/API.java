package org.LibreGainz;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

public class API {
  
    /**
     * send GET request to the server 
     * @param urlString
     * @return
     */
       public static String getResponseBody(String urlString){ 
            HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            //.header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

            HttpResponse<String> response = null;
            try{
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();

            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }
            catch(InterruptedException e){
                e.printStackTrace();
                return null;
            }
        }

/**
 * send POST request to the server
 * @param urlString
 * @param body
 * @return
 */
public static String post(String urlString, String body){ 
    HttpRequest request = HttpRequest.newBuilder()
	.POST(HttpRequest.BodyPublishers.ofString(body))
	.uri(URI.create(urlString))
	.header("Content-Type", "application/json")
	.build();
            try{
            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.statusCode());
            //System.out.println(response.body());
            return response.body();

            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }
            catch(InterruptedException e){
                e.printStackTrace();
                return null;
            }
        }

        public static String patch(String urlString, String body){ 
            HttpRequest request = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .uri(URI.create(urlString))
            .header("Content-Type", "application/json")
            .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
            .build();
                    try{
                    HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                    //System.out.println(response.statusCode());
                    //System.out.println(response.body());
                    return response.body();
        
                    }
                    catch(IOException e){
                        e.printStackTrace();
                        return null;
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                        return null;
                    }
                }












/**
 * Send a DELETE http request to the server
 * @param urlString
 * @return
 */
public static boolean delete(String urlString){ 
HttpRequest request = HttpRequest.newBuilder()
	.DELETE()
	.uri(URI.create(urlString))
    .method("DELETE", HttpRequest.BodyPublishers.noBody())
	.build();
    try{
    HttpResponse<String> response = HttpClient.newHttpClient()
	.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.statusCode());
    System.out.println(response.body());
    return true;
    } catch(Exception e){
        e.printStackTrace();
        return false;
    }

}















    }

      


