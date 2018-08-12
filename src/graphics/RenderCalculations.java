package graphics;

import objects.simple.Node;

/**
 * Created by Matt on 8/8/2016.
 */
public class RenderCalculations {

    public static Node rotateNode(Node n, double x, double y){
        NodeList nodeList = new NodeList(new Node[]{n}, null);
        nodeList.rotateX_Matrix(x);
        nodeList.rotateY_Matrix(y);
        return nodeList.list[0];
    }

}
