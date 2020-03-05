/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-09 buixuan.
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;
import specifications.StartEngineService;
import specifications.RequireStartEngineService;
import data.Position;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Viewer implements ViewerService, RequireReadService, RequireStartEngineService{
  private ReadService data;
  private StartEngineService startEngine;
  private static final double defaultMainWidth=HardCodedParameters.defaultWidth,
          defaultMainHeight=HardCodedParameters.defaultHeight;

  private double xShrink,yShrink,shrink,xModifier,yModifier,heroesScale;


  public Viewer(){}

  @Override
  public void bindReadService(ReadService service){
    data=service;
  }
  
  @Override
  public void bindStartEngineService(StartEngineService service){
    startEngine=service;
  }

  @Override
  public void init(){
	  xShrink=1;
	    yShrink=1;
	    xModifier=0;
	    yModifier=0;

  }

  @Override
  public void startViewer(){
    startEngine.start();
  }

  @Override
  public Parent getPanel(){
    
	  shrink=Math.min(xShrink,yShrink);
	    xModifier=.01*shrink*defaultMainHeight;
	    yModifier=.01*shrink*defaultMainHeight;
	  
	  Rectangle map = new Rectangle(HardCodedParameters.minX,HardCodedParameters.minY,(HardCodedParameters.maxX-HardCodedParameters.minX),(HardCodedParameters.maxY-HardCodedParameters.minY));
		map.setFill(Color.WHITE);
		map.setStroke(Color.DIMGRAY);
		map.setStrokeWidth(.01*shrink*defaultMainHeight);
		map.setArcWidth(.04*shrink*defaultMainHeight);
		map.setArcHeight(.04*shrink*defaultMainHeight);
		map.setTranslateX(xModifier);
		map.setTranslateY(yModifier);
		
		Group panel = new Group();
	    panel.getChildren().add(map);
	    
	    Circle heroesAvatar = new Circle(5,  Color.rgb(156,216,255));
	    heroesAvatar.setEffect(new Lighting());
	    heroesAvatar.setTranslateX(data.getHeroesPosition().x);
	    heroesAvatar.setTranslateY(data.getHeroesPosition().y);
	    
	    panel.getChildren().add(heroesAvatar);
	    
	    /*for (Position f: data.getFruits())
	    {
	    	Circle fruit = new Circle(2,  Color.MAGENTA);
	    	fruit.setTranslateX(f.x);
	    	fruit.setTranslateY(f.y);
	    	panel.getChildren().add(fruit);
	    }*/
    
    return panel;
  }
  
  @Override
  public void setMainWindowWidth(double width){
    xShrink=width/defaultMainWidth;
  }
  
  @Override
  public void setMainWindowHeight(double height){
    yShrink=height/defaultMainHeight;
  }
}
