package objects.simple;
public class Object_3D {

	private boolean visible;
	
	public Object_3D(){
		
	}
	
	public void set_visibility(boolean v){
		visible = v;
	}
	
	public boolean visibility(){
		return visible;
	}
	
	public boolean is_inside(Node p){
		return true;
	}

	public Node recursive_point_search(Node start, Node end){
		return recursive_point_search_function(start, end, 10, 0, false);
	}
	
	
	 public Node recursive_point_search_function(Node start, Node end, int recursion_depth, int current_depth,boolean everBeenIn){
		 Node mid = avg_point(start,end);
		 if(recursion_depth == current_depth){
			 if(!everBeenIn){
				 return new Node(1.0f/0.0f, 2.0f/0.0f, 3.0f/0.0f);
			 }
			 return mid; 
		 }
		 Node new_start;
		 Node new_end;
		 if(is_inside(mid)){
			 new_start = start;
			 new_end = mid;
			 everBeenIn = true;
		 }
		 else{
			 new_start = mid;
			 new_end = end;
		 }
		 return recursive_point_search_function(new_start, new_end, recursion_depth, current_depth + 1, everBeenIn);
		 
	 }
	 
	 
	public Node avg_point(Node one, Node two){
		 float avg_x = (one.getX() + two.getX()) / 2.0f;
		 float avg_y = (one.getY() + two.getY()) / 2.0f;
		 float avg_z = (one.getZ() + two.getZ()) / 2.0f;
		 return new Node(avg_x,avg_y,avg_z);
	}
	
	public boolean in_range(double p, double start, double end){
		if((p <= end && p >= start) || (p >= end && p <= start)){
			return true;
		}
		return false;
	}
	
	
	public Node stepwise_point_search(Node start, Node end){
		return stepwise_point_search_function(start, end, 300);
	}
	
	public Node stepwise_point_search_function(Node start, Node end1, int num_of_steps){
		
		Node end = new Node(start.getX() + 2*(end1.getX() - start.getX()),
							  start.getY() + 2*(end1.getY() - start.getY()),
							  start.getZ() + 2*(end1.getZ() - start.getZ()));
		
		float x_step = (end.getX() - start.getX()) / num_of_steps;
		float y_step = (end.getY() - start.getY()) / num_of_steps;
		float z_step = (end.getZ() - start.getZ()) / num_of_steps;
		
		for(int i = 0; i < num_of_steps; i++){
			Node test_Node = new Node(start.getX() + x_step*i, start.getY() + y_step*i, start.getZ() + z_step*i);
			if(is_inside(test_Node)){
				return test_Node;
			}
		}
		
		return new Node(4.0f/0.0f, 5.0f/0.0f, 6.0f/0.0f);
		
	}
	
	public double distance(Node one, Node two){
		double delta_x = one.getX() - two.getX();
		double delta_y = one.getY() - two.getY();
		double delta_z = one.getZ() - two.getZ();
		
		return Math.sqrt(delta_x * delta_x + delta_y * delta_y + delta_z * delta_z);
	}
	
	
	
}