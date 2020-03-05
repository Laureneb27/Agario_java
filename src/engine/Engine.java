/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: engine/Engine.java 2015-03-09 buixuan.
 * ******************************************************/
package engine;

import specifications.EngineService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.AlgorithmService;
import specifications.RequireAlgorithmService;

import data.Position;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements EngineService, RequireDataService, RequireAlgorithmService{
  private Timer engineClock;
  private DataService data;
  private AlgorithmService algorithm;
  private Random random;

  public Engine(){random = new Random();}

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void bindAlgorithmService(AlgorithmService service){
    algorithm=service;
  }
  
  @Override
  public void init(){
    engineClock = new Timer();
  }

  @Override
  public void start(){
    algorithm.activation();
    engineClock.schedule(new TimerTask(){
      public void run() {
        System.out.println("Game step #"+data.getStepNumber()+": checked.");
        algorithm.stepAction();
        if (ding()) data.addFruit();
        data.setStepNumber(data.getStepNumber()+1);
      }
    },0,100);
  }
  
  private boolean ding() {return random.nextInt(100)<=20;}
  
  @Override
  public void moveLeft(){
	  if (data.getHeroesPosition().x-1>data.getMinX())
		  data.setHeroesPosition(new Position(data.getHeroesPosition().x-1,data.getHeroesPosition().y));
    
  }
  
  @Override
  public void moveRight(){
	  if (data.getHeroesPosition().x+1<data.getMaxX())
		  data.setHeroesPosition(new Position(data.getHeroesPosition().x+1,data.getHeroesPosition().y));
    
  }
  
  @Override
  public void moveUp(){
	  if (data.getHeroesPosition().y-1>data.getMinY())
		  data.setHeroesPosition(new Position(data.getHeroesPosition().x,data.getHeroesPosition().y-1));
  }
  
  @Override
  public void moveDown(){
	  if (data.getHeroesPosition().y+1<data.getMaxY())
		  data.setHeroesPosition(new Position(data.getHeroesPosition().x,data.getHeroesPosition().y+1));
  }
}
