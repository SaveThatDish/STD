package com.example.savethatdish;

import com.parse.ParseObject;



public class Dish {

   private String name;
   private String restaurant;
   ParseObject dish;

   public Dish(String dishName, String restaurantName) {
      this.name = dishName;
      this.restaurant = restaurantName;
      
      dish = new ParseObject("Dish");
      dish.put("name", name);
      //link to restaurant
   }

   // delete local copy, connect to parse?
   public void removeDish() {
   }

   /* 
    * @param: rating
    */
   public void rateDish(int rating) // integer or boolean param?
   {

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
   public boolean addBucketlist(String userName) {
      return false;
   }

}
