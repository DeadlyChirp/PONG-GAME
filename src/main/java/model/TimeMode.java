package model;

import java.util.*;

import javax.swing.JPanel;

import model.Court;


public class TimeMode extends Court {

   private static int tmp;
   private static int scoreA = 0;
   private static int scoreB = 0;
    

    public TimeMode(RacketController playerA, RacketController playerB, double width, double height, int t) {
        super(playerA, playerB, width, height);
        Timer b = new Timer();
        tmp = t;
        TimerTask a = new TimerTask() {
    
            public void run() {
             if (tmp != 0) tmp--;
              else {
                reset();
                tmp = t;
            }
          }
            };
            
              b.scheduleAtFixedRate(a, 1000, 1000);
            
    }

    public static int getTmp() {
      return tmp;
    }

    public static int getScoreA() {
      return scoreA;
    }

    public static int getScoreB() {
      return scoreB;
    }

    public static void setScoreA(int a) {
      scoreA = a;
    }

    public static void setScoreB(int a) {
      scoreB = a;
    }


} 
