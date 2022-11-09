package com.gbrramos.lix.models;

import java.io.Serializable; 
public class JwtResponse implements Serializable {
   /**
   *
   */
   private static final long serialVersionUID = 1L;
   private User user;
   private final String token;
   public JwtResponse(String token, User user) {
      this.token = token;
      this.user = user;
   }
   public String getToken() {
      return token;
   }
   public User getUser() {
      return user;
   }
   public void setUser(User user) {
      this.user = user;
   }

   
}