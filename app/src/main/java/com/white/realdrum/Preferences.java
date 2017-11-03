package com.white.realdrum;

import android.content.SharedPreferences;

import com.white.realdrum.Base;

public class Preferences {

   private static final String ANIMADO_PREF = "com.white.realdrum.animado";
   public static final int BLOCK = 1;
   public static final int COWBELL = 2;
   private static final String CRASH_2_TO_CHINA_PREF = "com.white.realdrum.crash2tochina";
   private static final String EXAMPLES_COPIED = "com.white.realdrum.examplescopied";
   private static final String FLOOR_LEFT_PREF = "com.white.realdrum.floorleft";
   public static final int KICK_2 = 0;
   private static final String KICK_2_TO_PREF = "com.white.realdrum.kick2to";
   private static final String REPEAT_PREF = "com.white.realdrum.repeat";
   private static final String RIMSHOT_PREF = "com.white.realdrum.rimshot";
   private static final String RKADL = "com.white.realdrum.rkadl";
   public static final int TAMBOURINE = 3;
   private static final String VIBRATE_PREF = "com.white.realdrum.vibrate";
   private static boolean animado;
   private static Base base;
   private static boolean crash2tochina;
   private static boolean examplesCopied;
   private static boolean floorLeft;
   private static int kick2To;
   private static boolean repeat;
   private static boolean rimshot;
   private static boolean rkadl;
   private static SharedPreferences sharedPreferences;
   private static boolean vibrate;


   public static int getKick2To() {
      return kick2To;
   }

   public static void initPreferences(SharedPreferences var0, Base var1) {
      sharedPreferences = var0;
      base = var1;
      rkadl = sharedPreferences.getBoolean("com.white.realdrum.rkadl", false);
      animado = sharedPreferences.getBoolean("com.white.realdrum.animado", true);
      repeat = sharedPreferences.getBoolean("com.white.realdrum.repeat", true);
      vibrate = sharedPreferences.getBoolean("com.white.realdrum.vibrate", false);
      floorLeft = sharedPreferences.getBoolean("com.white.realdrum.floorleft", false);
      examplesCopied = sharedPreferences.getBoolean("com.white.realdrum.examplescopied", false);
      rimshot = sharedPreferences.getBoolean("com.white.realdrum.rimshot", false);
      crash2tochina = sharedPreferences.getBoolean("com.white.realdrum.crash2tochina", false);
      kick2To = sharedPreferences.getInt("com.white.realdrum.kick2to", 0);
   }

   public static boolean isAnimado() {
      return animado;
   }

   public static boolean isCrash2ToChina() {
      return crash2tochina;
   }

   public static boolean isExamplesCopied() {
      return examplesCopied;
   }

   public static boolean isFloorLeft() {
      return floorLeft;
   }

   public static boolean isRepeat() {
      return repeat;
   }

   public static boolean isRimshot() {
      return rimshot;
   }

   public static boolean isRkadl() {
      return rkadl;
   }

   public static boolean isVibrate() {
      return vibrate;
   }

   public static void setAnimado(boolean var0) {
      animado = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.animado", var0).commit();
   }

   public static void setCrash2ToChina(boolean var0) {
      crash2tochina = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.crash2tochina", var0).commit();
      base.montaTela();
   }

   public static void setExamplesCopied(boolean var0) {
      examplesCopied = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.examplescopied", var0).commit();
   }

   public static void setFloorLeft(boolean var0) {
      floorLeft = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.floorleft", var0).commit();
   }

   public static void setKick2To(int var0) {
      kick2To = var0;
      sharedPreferences.edit().putInt("com.white.realdrum.kick2to", var0).commit();
      base.montaTela();
   }

   public static void setRepeat(boolean var0) {
      repeat = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.repeat", var0).commit();
   }

   public static void setRimshot(boolean var0) {
      rimshot = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.rimshot", var0).commit();
      base.montaTela();
   }

   public static void setRkadl(boolean var0) {
      rkadl = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.rkadl", var0).commit();
   }

   public static void setVibrate(boolean var0) {
      vibrate = var0;
      sharedPreferences.edit().putBoolean("com.white.realdrum.vibrate", var0).commit();
   }
}
