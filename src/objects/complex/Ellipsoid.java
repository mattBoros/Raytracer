package objects.complex;
import objects.simple.Node;
import objects.simple.Object_3D;

public class Ellipsoid extends Object_3D{

	Node center;
	double a,b,c;

	public Ellipsoid(Node center1, double a1, double b1, double c1){
		a = a1;
		b = b1;
		c = c1;
		center = center1;
	}
	
	public boolean is_inside(Node p){
		double delta_x = p.getX() - center.getX();
		double delta_y = p.getY() - center.getY();
		double delta_z = p.getZ() - center.getZ();
		
		double x2 = delta_x * delta_x /a / a;
		double y2 = delta_y * delta_y /b / b;
		double z2 = delta_z * delta_z /c / c;
		
		if(x2 + y2 + z2 < 1){
			return true;
		}
		
		return false;
	}
	
	public Node find_intersection(Node start, Node end){
		return recursive_point_search(start, end);
	}

}
