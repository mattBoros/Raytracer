package main;
import graphics.*;
import graphics.Canvas;
import objects.complex.Box;
import objects.complex.Sphere;
import objects.simple.Node;
import objects.simple.Object_3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.IntStream;

public class Renderer {

	public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    public static final float REAL_WIDTH = 6;
    public static final float REAL_HEIGHT = 6;

    public static final int ACCURACY = 415;


    public final Canvas canvas;
    public final NodeList nodes;


    public Renderer(MultiObject all_objects){
        canvas = Canvas.getCanvas("Display");

        boolean[][][] object_quantized = process_object(all_objects, ACCURACY);
        Printer.print("processed");
        ArrayList<Node> nodesWithoutColors = new ArrayList<>();

        for(int x = 0; x < object_quantized.length; x++){
            for(int y = 0; y < object_quantized[x].length; y++){
                for(int z = 0; z < object_quantized[x][y].length; z++){

                    if(object_quantized[x][y][z]){
                        nodesWithoutColors.add(new Node(x, y, z, ACCURACY));
                    }

                }
            }
        }

        Color[] node_colors = new Color[nodesWithoutColors.size()];
        for(int i = 0; i < node_colors.length; i++){
            node_colors[i] = Node.get_node_color(nodesWithoutColors.get(i));
        }
        Node[] asArray = new Node[nodesWithoutColors.size()];
        nodesWithoutColors.toArray(asArray);
        nodes = new NodeList(asArray, node_colors);
    }



	public static void main(String[] args) {
        Node origin = new Node(0, 0,0);

        Renderer renderer = new Renderer(new MultiObject()
                                                 .add_object(new Sphere(origin, 1.5f), true)
                                                 .add_object(new Box(origin, 4f, 1.5f, 1.5f), false)
                                                );
        FrameManager manager = new FrameManager(40);
        while(true){
            manager.startFrame();
            renderer.rotate(0.05f, 0.05f);
            renderer.canvas.fill(Color.BLACK);
            renderer.display();
            manager.endFrame();
            renderer.canvas.repaint();
        }
	}





	public void rotate(float x, float y){
		nodes.rotateX_Matrix(x);
		nodes.rotateY_Matrix(y);
	}


    private static final AlgorithmTimer displayTimer = new AlgorithmTimer("display", 3);
	public void display(){
	    if(displayTimer.shouldRunAlgorithm(0)){
            displayTimer.algorithmStart(0);

            final Float[][] pixel_used_reference = new Float[canvas.canvas_width][canvas.canvas_height];
            for(int i = 0; i < nodes.size; i++){
                Node n = nodes.list[i];
                int[] p = real_coordinates_to_screen_coordinates(n.coordinates[0],n.coordinates[1]);

                Float is_used = pixel_used_reference[p[0]][p[1]];

                if(is_used == null || is_used > n.coordinates[2]){
                    canvas.setColor(p[0],p[1], nodes.c_list[i]);
                    pixel_used_reference[p[0]][p[1]] = n.coordinates[2];
                }
            }

	        displayTimer.algorithmEnd(0);
        }



        if(displayTimer.shouldRunAlgorithm(1)){
            displayTimer.algorithmStart(1);

            final Float[][] pixel_used_reference = new Float[canvas.canvas_width][canvas.canvas_height];
            IntStream.range(0, nodes.size)
                    .parallel()
                    .forEach(i -> {
                        Node n = nodes.list[i];
                        int[] p = real_coordinates_to_screen_coordinates(n.coordinates[0],n.coordinates[1]);

                        Float is_used = pixel_used_reference[p[0]][p[1]];

                        if(is_used == null || is_used > n.coordinates[2]){
                            canvas.setColor(p[0],p[1], nodes.c_list[i]);
                            pixel_used_reference[p[0]][p[1]] = n.coordinates[2];
                        }
                    });
	        displayTimer.algorithmEnd(1);
        }

        if(displayTimer.shouldRunAlgorithm(2)){
            displayTimer.algorithmStart(2);

            final Float[][] pixel_used_reference = new Float[canvas.canvas_width][canvas.canvas_height];
            IntStream.range(0, nodes.size)
                    .forEach(i -> {
                        Node n = nodes.list[i];
                        int[] p = real_coordinates_to_screen_coordinates(n.coordinates[0],n.coordinates[1]);

                        Float is_used = pixel_used_reference[p[0]][p[1]];

                        if(is_used == null || is_used > n.coordinates[2]){
                            canvas.setColor(p[0],p[1], nodes.c_list[i]);
                            pixel_used_reference[p[0]][p[1]] = n.coordinates[2];
                        }
                    });
            displayTimer.algorithmEnd(2);
        }

	}



    public static int[] real_coordinates_to_screen_coordinates(float x, float y){
        float leastX = -1.0f * REAL_WIDTH / 2.0f;
        float maxX = REAL_WIDTH / 2.0f;

        float leastY = -1.0f * REAL_HEIGHT / 2.0f;
        float maxY = REAL_HEIGHT / 2.0f;

        float x2 = x*WIDTH/(maxX - leastX) + (WIDTH * leastX)/(leastX - maxX);
        float y2 = y*HEIGHT/(leastY - maxY) +  (HEIGHT * maxY)/(maxY - leastY);
        int x3 = Math.round(x2);
        int y3 = Math.round(y2);
        return new int[]{x3,y3};
    }
    
    
    
    
    private static final AlgorithmTimer processMostInner = new AlgorithmTimer("process_inner",3, 400);
	public static boolean[][][] process_object(Object_3D object, int accuracy){
		final boolean[][][] ret_arr = new boolean[accuracy][accuracy][accuracy];
		
		float x_min = -3;
		float x_max = 3;

		float y_min = -3;
		float y_max = 3;

		float z_min = -3;
		float z_max = 3;

		float x_step = (x_max - x_min) / accuracy;
		float y_step = (y_max - y_min) / accuracy;
		float z_step = (z_max - z_min) / accuracy;



		for(int t_x = 0; t_x < accuracy; t_x++){
		    final int t_x_final = t_x;

		    Printer.print(100.0*t_x/accuracy + "%");
			for(int t_y = 0; t_y < accuracy; t_y++){
			    final int t_y_final = t_y;

                if(processMostInner.shouldRunAlgorithm(0)){
                    processMostInner.algorithmStart(0);
                    for(int t_z = 0; t_z < accuracy; t_z++){
                        float x = x_min + t_x_final * x_step;
                        float y = y_min + t_y_final * y_step;
                        float z = z_min + t_z * z_step;
                        ret_arr[t_x_final][t_y_final][t_z] = object.is_inside(new Node(x,y,z));
                    }
                    processMostInner.algorithmEnd(0);
                }


			    if(processMostInner.shouldRunAlgorithm(1)){
                    processMostInner.algorithmStart(1);
                    IntStream.range(0, accuracy)
                            .unordered()
                            .parallel()
                            .forEach(t_z -> {
                                float x = x_min + t_x_final * x_step;
                                float y = y_min + t_y_final * y_step;
                                float z = z_min + t_z * z_step;
                                ret_arr[t_x_final][t_y_final][t_z] = object.is_inside(new Node(x,y,z));
                            });
			        processMostInner.algorithmEnd(1);
                }

                if(processMostInner.shouldRunAlgorithm(2)){
                    processMostInner.algorithmStart(2);
                    IntStream.range(0, accuracy)
                            .unordered()
                            .forEach(t_z -> {
                                float x = x_min + t_x_final * x_step;
                                float y = y_min + t_y_final * y_step;
                                float z = z_min + t_z * z_step;
                                ret_arr[t_x_final][t_y_final][t_z] = object.is_inside(new Node(x,y,z));
                            });
                    processMostInner.algorithmEnd(2);
                }


			}
		}

		Printer.print("checking neighbors");

		return checkForUnnecessaryNeighbors(ret_arr);
	}

    public static boolean areAllZero(int... nums){
        return Arrays.stream(nums)
                .unordered()
                .allMatch(i -> i==0);
    }

    public static boolean hasAllNeighbors(boolean[][][] arr, int x, int y, int z){
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                for(int k = -1; k < 2; k++){
                    if(!areAllZero(i, j, k) && !arr[x+i][y+j][z+k]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean[][][] checkForUnnecessaryNeighbors(boolean[][][] arr){
        Vector<int[]> pointsToRemove = new Vector<>();

        IntStream.range(1, arr.length - 1)
                .unordered()
                .parallel()
                .forEach( x -> {
                                IntStream.range(1, arr[x].length - 1)
                                        .unordered()
                                .parallel()
                                .forEach(y ->{
                                    for(int z = 1; z < arr[x][y].length - 1; z++){
                                    if(arr[x][y][z] && hasAllNeighbors(arr, x, y, z)){
                                        pointsToRemove.add(new int[]{x, y, z});
                                    }
                                }
                            });
                        }
                );
        for(int[] point : pointsToRemove){
            int x = point[0];
            int y = point[1];
            int z = point[2];
            arr[x][y][z] = false;
        }
        return arr;
    }

}

