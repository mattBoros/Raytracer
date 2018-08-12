package objects.complex;

import objects.simple.Node;
import objects.simple.Object_3D;

public class Torus extends Object_3D{

	Node center;
	double hole_R;
	double tube_r;
	
	public Torus(Node center1, double hole_radius, double tube_radius){
		center = center1;
		hole_R = hole_radius;
		tube_r = tube_radius;
	}

	public boolean is_inside(Node p){
		
		double x = p.getX() - center.getX();
		double y = p.getY() - center.getY();
		double z = p.getZ() - center.getZ();
		
		double a = hole_R;
		double b = tube_r;
		
		double test_inside =
				Math.pow(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2)-(Math.pow(a,2)+Math.pow(b,2)),2) - 4*a*b*(Math.pow(b,2)-Math.pow(z, 2));
				if(test_inside < 0){
					return true;
				}
		return false;
		
	}
	
	public Node find_intersection(Node start, Node end){
		return stepwise_point_search(start, end);
	}
	
}
