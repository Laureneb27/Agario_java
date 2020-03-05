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
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Viewer implements ViewerService, RequireReadService, RequireStartEngineService{
  private ReadService data;
  private StartEngineService startEngine;

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
  }

  @Override
  public void startViewer(){
    startEngine.start();
  }

  @Override
  public Group getPanel(){
    Circle heroesAvatar = new Circle(2,  Color.rgb(156,216,255));
    heroesAvatar.setEffect(new Lighting());
    heroesAvatar.setTranslateX(data.getHeroesPosition().x);
    heroesAvatar.setTranslateY(data.getHeroesPosition().y);
    
    Rectangle border = new Rectangle(HardCodedParameters.minX,HardCodedParameters.minY,(HardCodedParameters.maxX-HardCodedParameters.minX),(HardCodedParameters.maxY-HardCodedParameters.minY));
    
    Group panel = new Group();
    panel.getChildren().add(border);
    panel.getChildren().add(heroesAvatar);
    
    for (Position f: data.getFruits())
    {
    	Circle fruit = new Circle(2,  Color.AQUAMARINE);
    	fruit.setTranslateX(f.x);
    	fruit.setTranslateY(f.y);
    	panel.getChildren().add(fruit);
    }
    
    return panel;
  }
}
