package objects.complex;
import objects.simple.Node;
import objects.simple.Object_3D;
public class Sphere extends Object_3D{

	Node center;
	float radius;
	
	public Sphere(Node c, float r){
		center = c;
		radius = r;
	}
	
	public boolean is_inside(Node p){
		double distance = distance(center, p);
		if(distance < radius){
			return true;
		}
		return false;
	}

	
	
	public Node find_intersection(Node start, Node end){
		return calculated_Node_search(start, end);
	}
	
	public Node calculated_Node_search(Node start, Node end){
		float x_step = end.getX() - start.getX();
		float y_step = end.getY() - start.getY(); 
		float z_step = end.getZ() - start.getZ();
		
		float a = x_step*x_step + y_step * y_step + z_step * z_step;
		float b = 2 * (start.getX()*x_step + start.getY()*y_step + start.getZ()*z_step);
		float c = (start.getX()*start.getX() + start.getY()*start.getY() + start.getZ()*start.getZ()) - radius*radius;
		
		float[] intersections = quadratic(a,b,c);
		
		if(Double.isNaN(intersections[0]) && Double.isNaN(intersections[1])){
			return new Node(4.0f/0.0f,3.0f/0.0f,5.0f/0.0f);
		}
		
		Node p1 = new Node(start.getX() + x_step*intersections[0],
				start.getY() + y_step * intersections[0],
				start.getZ() + z_step * intersections[0]); 
		
		Node p2 =  new Node(start.getX() + x_step*intersections[1],
				start.getY() + y_step * intersections[1],
				start.getZ() + z_step * intersections[1]);
		
		if(distance(start, p1) < distance(start, p2)){
			return p1; 
		}
		else{
			return p2;
		}
		
	}
	
	public float[] quadratic(float a, float b, float c){

		float return1 = (-1*b + ((float) Math.sqrt(b*b - 4*a*c)) ) / 2 / a;
		float return2 = (-1*b - ((float) Math.sqrt(b*b - 4*a*c)) ) / 2 / a;
		
		float[] ret = new float[2];
		ret[0] = return1;
		ret[1] = return2;
		return ret;
				
	}
	
	
}
