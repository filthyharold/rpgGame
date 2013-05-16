package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Box2dWorldManager {

	private World world;
	private Box2DDebugRenderer debugRenderer;
	
	private ArrayList<Body> bodies = new ArrayList<Body>();
	private Shape tmpShape;
	private Body tmpBody;
	private BodyDef tmpBodyDef;
	
	public Box2dWorldManager()
	{	
		world = new World(new Vector2(0, -5), true);		
		debugRenderer = new Box2DDebugRenderer();
		
		tmpBodyDef = new BodyDef();
	}
	
	public void createBodies(ArrayList<Box2dMapObjectData> objData)
	{		
		for (Box2dMapObjectData od : objData) {
			tmpBodyDef.type = od.bodyType;
			tmpBodyDef.position.set(od.pos);
			
			tmpBody = world.createBody(tmpBodyDef);
			
			if(od.shapeType == Shape.Type.Circle)
			{
				tmpShape = new CircleShape();
				tmpShape.setRadius(16 * Constants.WORLD_TO_BOX);
			}
			else //default to a box
			{
				//why cant I use SetAsBox?
				tmpShape = new PolygonShape();
				tmpShape.setRadius(16 * Constants.WORLD_TO_BOX);
			}
			
			tmpBody.createFixture(tmpShape, 0.0f);
			
			tmpShape.dispose();
				
//			bodies.add();
		}
	}
	
	public void  destroyBodies()
	{
		
	}
	
	public void destroyBodyAt(int x, int y)
	{
		
	}
	
	public void destroyBodyAt(int index)
	{}
	
	public void update(float timeStep)
	{
		world.step(1/60f,6, 2);
	}
	
	public void renderDebug(Matrix4 projMatrix)
	{
		debugRenderer.render(this.world, projMatrix);
	}
	
}
