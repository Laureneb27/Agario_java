/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-09 buixuan.
 * ******************************************************/
package data;

import java.util.ArrayList;
import java.util.Random;

import specifications.DataService;
import tools.HardCodedParameters;

public class Data implements DataService{
  //private Heroes hercules;
  Position heroesPosition;
  int stepNumber;
  int minX,maxX,minY,maxY;
  ArrayList<Position> fruits;
  Random random;

  public Data(){}
  
  public void init() {
	//hercules = new Heroes;
	    heroesPosition = new Position(250,150);
	    stepNumber = 0;
	    minX = HardCodedParameters.minX;
	    maxX = HardCodedParameters.maxX;
	    minY = HardCodedParameters.minY;
	    maxY = HardCodedParameters.maxY;
	    
	    fruits = new ArrayList<Position>();
	    random = new Random();
  }

  @Override
  public Position getHeroesPosition(){ return heroesPosition; }

  @Override
  public int getStepNumber(){ return stepNumber; }

  @Override
  public void setHeroesPosition(Position p) { heroesPosition = p; }
  
  @Override
  public void setStepNumber(int n){ stepNumber=n; }
 

  @Override
  public int getMinX(){ return minX; }
  
  @Override
  public int getMaxX(){ return maxX; }

  @Override
  public int getMinY(){ return minY; }
  
  @Override
  public int getMaxY(){ return maxY; }
  
  @Override
  public ArrayList<Position> getFruits(){ return fruits; }
  
  @Override
  public void addFruit(){
    if (fruits.size()>(maxX-minX-10)*(maxY-minY-10)) return;
    int newX, newY;
    boolean found = false;
    while (!found) {
      newX = random.nextInt(maxX)+minX;
      newY = random.nextInt(maxY)+minY;
      found = true;
      for (Position f: fruits) if (f.x==newX && f.y==newY) found = false;
      if (found) fruits.add(new Position(newX,newY));
    }
    
  }
  @Override
  public void removeFruit(Position f){ fruits.remove(f); }

}
