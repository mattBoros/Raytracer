package objects.complex;

import objects.simple.Node;
import objects.simple.Object_3D;

public class Fractal extends Object_3D{


	Node camera_position;
	Node object_position;
	
	int drawing_accuracy;
	
	public Fractal(Node cam_pos, Node object_pos, int drawing_acc){
		camera_position = cam_pos;
		object_position = object_pos;
		
		drawing_accuracy = drawing_acc;
	}
	
	
	
	
	private double[] add(double[] one, double[] two){
		double[] ret = new double[5];
		for(int i = 0; i < 3; i++){
			ret[i] = one[i] + two[i];
		}
		return ret;
	}
	
	
	private double[] multiply(double[] one, double[] two){
		double x,y,z;
		if (one[0]*one[0] == -1 * one[1]*one[1]){
	        x = 0;
		}
	    else{
	        x  =  ((one[0]*one[0]-one[1]*one[1]) * (1-(one[2]*one[2]) / (one[0]*one[0]+one[1]*one[1])));
	    }
	    if (one[0]*one[0] == -1*one[1]*one[1]){
	        y = 0;
	    }
	    else{
	        y  =  (2*one[0]*one[1] * (1-(one[2]*one[2]) / (one[0]*one[0]+one[1]*one[1])));
	    }
	    z  =  (-2*one[2] * Math.sqrt(one[0]*one[0]+one[1]*one[1]));
	    double[] ret = {x,y,z};
	    return ret;
	}
	
	
	private double absolute_value(double[] pos){
		
		Double ret = Math.sqrt(pos[0]*pos[0] + pos[1]*pos[1] + pos[2]*pos[2]);
		return ret;
	}

	
	public boolean is_inside(Node p){
		double[] Node = {p.getX(), p.getY(), p.getZ()};
		double[] constant = Node;
		int counter = 0;
		while(counter < drawing_accuracy && absolute_value(Node) < 2.0){
			Node = add(constant, multiply(Node,Node));
			counter += 1;
		}
		if (counter == drawing_accuracy){
			return true;
		}
		return false;
	}
	
	public Node find_intersection(Node start, Node end){
		return stepwise_point_search(start, end);
	}

}
