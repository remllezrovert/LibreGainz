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
           /*  try {
                Template t = new Template(0);
                ObjectMapper objectMapper = new ObjectMapper();
                List<Strength> list = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Strength.class));
                
                for(Strength s : list){
                    System.out.println(s.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //System.out.println(response.body());
            //System.out.println(Strength.jsonParse(response.body()).toString());



                















        }

      


