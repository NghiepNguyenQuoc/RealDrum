package com.white.realdrum;

import com.white.realdrum.Base;
import com.white.realdrum.Preferences;

import org.anddev.andengine.audio.sound.Sound;

public class Sons {

   public static final int CLOSE_HH = 12;
   public static final int CRASH_1 = 7;
   public static final int CRASH_2 = 8;
   public static final int FLOOR = 6;
   public static final int KICK_1 = 1;
   public static final int KICK_2 = 14;
   public static final int OPEN_HH = 11;
   public static final int RIDE = 10;
   public static final int RIMSHOT = 15;
   public static final int SINE = 13;
   public static final int SNARE = 2;
   public static final int SPLASH = 9;
   public static final int TOM_1 = 3;
   public static final int TOM_2 = 4;
   public static final int TOM_3 = 5;
   private Base base;
   private Sound block;
   private Sound cowbell;
   private Sound china;
   private Sound closehh;
   private Sound tambourine;
   private Sound crash1;
   private Sound crash2;
   private Sound floor;
   private Sound kick;
   private Sound openhh;
   private Sound ride;
   private Sound sine;
   
   private Sound rimshot;
   private Sound snare;

   private Sound splash;
   private Sound tom1;
   private Sound tom2;
   private Sound tom3;


   public Sons(Base var1) {
      this.base = var1;
   }

   public void play(int var1) {
      label32:
      switch(var1) {
      case 1:
         this.kick.play();
         break;
      case 2:
         this.snare.play();
         break;
      case 3:
         this.tom1.play();
         break;
      case 4:
         this.tom2.play();
         break;
      case 5:
         this.tom3.play();
         break;
      case 6:
         this.floor.play();
         break;
      case 7:
         this.crash1.play();
         break;
      case 8:
         if(Preferences.isCrash2ToChina()) {
            this.china.play();
         } else {
            this.crash2.play();
         }
         break;
      case 9:
         this.splash.play();
         break;
      case 10:
         this.ride.play();
         break;
      case 11:
         this.openhh.play();
         break;
      case 12:
         this.openhh.stop();
         this.closehh.play();
         break;
      case 13:
         this.sine.play();
         break;
      case 14:
         switch(Preferences.getKick2To()) {
         case 0:
            this.kick.play();
            break label32;
         case 1:
            this.block.play();
            break label32;
         case 2:
            this.cowbell.play();
            break label32;
         case 3:
            this.tambourine.play();
         default:
            break label32;
         }
      case 15:
         this.rimshot.play();
      }

      this.base.gravarNota(var1);
   }

   public void setBlock(Sound var1) {
      this.block = var1;
   }

   public void setChina(Sound var1) {
      this.china = var1;
   }

   public void setClosehh(Sound var1) {
      this.closehh = var1;
   }

   public void setCowbell(Sound var1) {
      this.cowbell = var1;
   }

   public void setCrash1(Sound var1) {
      this.crash1 = var1;
   }

   public void setCrash2(Sound var1) {
      this.crash2 = var1;
   }

   public void setFloor(Sound var1) {
      this.floor = var1;
   }

   public void setKick(Sound var1) {
      this.kick = var1;
   }

   public void setOpenhh(Sound var1) {
      this.openhh = var1;
   }

   public void setRide(Sound var1) {
      this.ride = var1;
   }

   public void setRimshot(Sound var1) {
      this.rimshot = var1;
   }

   public void setSine(Sound var1) {
      this.sine = var1;
   }

   public void setSnare(Sound var1) {
      this.snare = var1;
   }

   public void setSplash(Sound var1) {
      this.splash = var1;
   }

   public void setTambourine(Sound var1) {
      this.tambourine = var1;
   }

   public void setTom1(Sound var1) {
      this.tom1 = var1;
   }

   public void setTom2(Sound var1) {
      this.tom2 = var1;
   }

   public void setTom3(Sound var1) {
      this.tom3 = var1;
   }
}
