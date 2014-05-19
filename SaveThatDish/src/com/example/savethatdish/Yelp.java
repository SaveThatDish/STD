package com.example.savethatdish;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Yelp {

   OAuthService service;
   Token accessToken;

   /**
    * Setup the Yelp API OAuth credentials.
    *
    * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
    *
    * @param consumerKey Consumer key
    * @param consumerSecret Consumer secret
    * @param token Token
    * @param tokenSecret Token secret
    */
   public Yelp(String consumerKey, String consumerSecret, String token,
         String tokenSecret) {
      this.service = new ServiceBuilder().provider(YelpApi2.class)
            .apiKey(consumerKey).apiSecret(consumerSecret).build();
      this.accessToken = new Token(token, tokenSecret);
   }

   public String search(String term, String sort, String location) {
      OAuthRequest request = new OAuthRequest(Verb.GET,
            "http://api.yelp.com/v2/search");
      request.addQuerystringParameter("term", term);
      request.addQuerystringParameter("sort", sort);
      request.addQuerystringParameter("location", location);
      this.service.signRequest(this.accessToken, request);
      Response response = request.send();
      return response.getBody();
   }

 /** public static void main(String[] args) throws JSONException {

      String consumerKey = "PYJ9fp4Zs357x8GKEcc2OA";
      String consumerSecret = "Svw5yWnPK26_WYOrbkcvsC4PMNU";
      String token = "_o14KmTq969arSh-BdJBIHIBLanS3h2J";
      String tokenSecret = "MLFvYopfLQVy8YWpN7WObb8u_EA";

      Scanner input = new Scanner(System.in);
      String term = input.nextLine();
      String sort = input.nextLine();
      String location = input.nextLine();
      // example use of Yelp's API
      Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
      String response = yelp.search(term, sort, location);

      JSONObject obj = new JSONObject(response);
      JSONArray businesses = obj.getJSONArray("businesses");
      
      int n = businesses.length();
      for(int i = 0; i < n; i++) {
    	  System.out.println(businesses.getJSONObject(i).getString("name"));
      }
      System.out.println(response);
      
      input.close();
   }*/
}