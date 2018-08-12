package graphics;

import java.awt.Color;
import java.util.stream.IntStream;

import main.AlgorithmTimer;
import objects.simple.Node;

public class NodeList {

	public Node[] list;
	public Color[] c_list = null;
	public final int size;
	
	public NodeList(Node[] node_list, Color[] color_list){
		list = node_list;
		c_list = color_list;
		size = list.length;

	}

	private static final AlgorithmTimer multiplyTimer = new AlgorithmTimer("multiply", 3, 150);
    public void multiply(double[][] B) {

        if(multiplyTimer.shouldRunAlgorithm(0)){
            multiplyTimer.algorithmStart(0);

            Node[] c = new Node[size];
            for(int i = 0; i < size; i++){
                c[i] = new Node();
            }

            for(int i = 0; i < size; i++){
                multiplyIndividual(c[i], B, list, i);
            }

            list = c;

            multiplyTimer.algorithmEnd(0);
        }

        if(multiplyTimer.shouldRunAlgorithm(1)){
            multiplyTimer.algorithmStart(1);

            Node[] c = new Node[size];
            for(int i = 0; i < size; i++){
                c[i] = new Node();
            }

            IntStream.range(0, size)
                    .unordered()
                    .parallel()
                    .forEach(i->multiplyIndividual(c[i], B, list, i));

            list = c;

            multiplyTimer.algorithmEnd(1);
        }

        if(multiplyTimer.shouldRunAlgorithm(2)) {
            multiplyTimer.algorithmStart(2);

            Node[] c = new Node[size];
            for(int i = 0; i < size; i++){
                c[i] = new Node();
            }

            IntStream.range(0, size)
                    .unordered()
                    .forEach(i->multiplyIndividual(c[i], B, list, i));


            list = c;

            multiplyTimer.algorithmEnd(2);
        }
    }

	public static Node multiplyIndividual(Node c, double[][] B, Node[] list, int i){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 4; k++){
                if(k == 3){
                    c.coordinates[j] += B[k][j];
                } else {
                    c.coordinates[j] += list[i].coordinates[k] * B[k][j];
                }
            }
        }
        return c;
    }

	public void rotateX_Matrix(double radians){
		
		double theta = Math.cos(radians);
		double phi   = Math.sin(radians);
		
		double[][] x_rot = {{1,0,    0,     0},
							{0,theta,-1*phi,0},
							{0,phi,  theta, 0},
							{0,0,    0,     1}};
		
		multiply(x_rot);
	}

	public void rotateY_Matrix(double radians){
		
		double theta = Math.cos(radians);
		double phi   = Math.sin(radians);
		
		double[][] y_rot = {{theta,0,phi,0},
							{0,1,0,0},
							{-1*phi,0,theta,0},
							{0,0,0,1}};
		
		multiply(y_rot);
		
	}
	
	
	public void rotateZ_Matrix(double radians){
		
		double theta = Math.cos(radians);
		double phi   = Math.sin(radians);
		
		double[][] z_rot = {{theta,-1* phi, 0, 0},
							{phi, theta, 0, 0},
							{0,0,1,0},
							{0,0,0,1}};
		
		multiply(z_rot);
		
	}
	
	
}