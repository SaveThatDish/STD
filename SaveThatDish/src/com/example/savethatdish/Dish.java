package com.example.savethatdish;

import com.parse.ParseObject;


public class Dish {

   private String name;
   private String restaurant;
   ParseObject dish;

   //we need to store the location data, not restaurant name. location implicitly has res. name as well
   public Dish(String dishName, String restaurantName) { //what info is the location stored as... string? 
      this.name = dishName;
      this.restaurant = restaurantName;
      
      //if dish doesn't exist in parse
        //addToParse(); //check return type/error handling. what do we do if it fails?
      //else
        //do nothing?
      
      //should we create a ParseQuery object? We'll need it for allLikes and onBucketList only, 
      //so maybe only create the object in the methods that actually need it?
      
         }

   /*
    * Creating the dish in the constructor doesn't make sense: what if the dish exists already and we're 
    * only interested in creating a local var for the sake of convenience. Instead create a method that does that.
    * 
    * @Return: boolean to check for success? 
    * OtheR: Error handling should be done within this method.  
    */
   
   private boolean addToParse() {
	   dish = new ParseObject("Dish"); 
	   //Associated info to add: restaurant name, location. Anything else?
	   return true; //need to do error handling before returning
   }
   
   /*
    * Purpose: delete local copy, connect to parse?
    * @Return: Boolean value indicating whether remove was successful
    * Other: Error Handling
    */
   
   public boolean removeDish() {
	   return true;
   }

   /* 
    * @param: rating
    * @return: Boolean value letting caller know if it was successful.
    * Other: Error handling with Parse should be done in this method. Use that
    * info to return the proper Boolean
    */
   public boolean rateDish(int rating) // integer or boolean param?
   {
	   return true;
   }

   /* 
    * @return: returns the number of total likes for a dish
    */
   public int allLikes() {
      return 0;
   }

   /* 
    * @param: userName
    * @return: returns true if dish is on bucketlist,
    *  	    returns false if dish is not on bucketlist
    */
   public boolean onBucketlist(String userName) {
      return false;
   }

   /* 
    * @param: userName
    * @return: returns true if successfully added to bucketlist,
    *  	    returns false if already on bucketlist
    */
   public boolean addToBucketlist(String userName) {
      if (onBucketlist(userName))
        return false;
      //else add to Parse
      return true;
   }

}
