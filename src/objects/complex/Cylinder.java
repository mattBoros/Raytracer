package objects.complex;
import graphics.RenderCalculations;
import objects.simple.Node;
import objects.simple.Object_3D;

public class Cylinder extends Object_3D {

	Node center;
	double radius;
	double height;

	private double xRot = 0;
	private double yRot = 0;
	
	public Cylinder(Node c, double h, double r){
		center = c;
		radius = r;
		height = h;
	}
	
	public boolean is_inside(Node p){
		p = RenderCalculations.rotateNode(p.copy(), -xRot, -yRot);
		if(p.getZ() <= center.getZ() + height/2.0 && p.getZ() >= center.getZ() - height/2.0){
			double delta_y = p.getY() - center.getY();
			double delta_x = p.getX() - center.getX();
			double distance = Math.sqrt(delta_y*delta_y + delta_x*delta_x);
			if(distance < radius){
				return true;
			}
		}
		return false;
	}
	
	public Node find_intersection(Node start, Node end){
		return recursive_point_search(start, end);
	}

	public Cylinder rotateX(double x){
		xRot += x;
		return this;
	}

	public Cylinder rotateY(double y){
		yRot += y;
		return this;
	}

	public Cylinder rotate(double x, double y){
		rotateX(x);
		rotateY(y);
		return this;
	}
	
}