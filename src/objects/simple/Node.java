package objects.simple;

import java.awt.Color;

public class Node{

	public float[] coordinates = new float[3];
	
	
	public Node(){
	}
	
	
	public Node(int x, int y, int z, int accuracy){
		float x1 = -3 + x*6.0f/accuracy;
		float y1 = -3 + y*6.0f/accuracy;
		float z1 = -3 + z*6.0f/accuracy;
		coordinates[0] = x1;
		coordinates[1] = y1;
		coordinates[2] = z1;	
	}
	
	public Node(float x, float y, float z){
		coordinates[0] = x;
		coordinates[1] = y;
		coordinates[2] = z;
	}
	
	public Node(float[] coord){
		coordinates = coord;
	}

	
	public static Color get_node_color(float[] coordinates){
		float c = 200;
		
		float r = Math.abs(coordinates[0]) * c;
		float g = Math.abs(coordinates[1]) * c;
		float b = Math.abs(coordinates[2]) * c;
		
		int r2 = (int) Math.max( Math.min(255, r), 0);
		int g2 = (int) Math.max( Math.min(255, g), 0);
		int b2 = (int) Math.max( Math.min(255, b), 0);
		
		return new Color(r2,g2,b2);
	}

	public static Color get_node_color(Node n){
        return get_node_color(n.coordinates);
    }
	
	public float getX(){
		return coordinates[0];	
	}
	public float getY(){
		return coordinates[1];
	}
	public float getZ(){
		return coordinates[2];
	}
	
	public void display(){
		System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);
	}

	public Node copy(){
		return new Node(coordinates[0], coordinates[1], coordinates[2]);
	}
}
