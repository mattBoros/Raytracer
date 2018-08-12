package graphics;

import java.util.ArrayList;
import java.util.List;

import objects.simple.Node;
import objects.simple.Object_3D;

public class MultiObject extends Object_3D{

	List<Object_3D> array = new ArrayList<>();
	
	public MultiObject(){

	}
	
	public MultiObject add_object(Object_3D object, boolean visibility){
		object.set_visibility(visibility);
		array.add(object);
		return this;
	}
	
	public boolean is_inside(Node p){
		for(int i = array.size() - 1; i > -1; i--){
			Object_3D current_obj = array.get(i);
			if(current_obj.is_inside(p)){
				return current_obj.visibility();
			}
		}
		return false;		
	}
	
	public Node find_intersection(Node start, Node end){
		return stepwise_point_search(start, end);
	}
	
	
}
