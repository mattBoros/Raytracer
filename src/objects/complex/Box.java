package objects.complex;

import java.util.ArrayList;

import objects.simple.Node;
import objects.simple.Object_3D;

public class Box extends Object_3D {

    Node center;
    final float width;
    final float height;
    final float depth;
    final float[] bound = new float[6];
    final float[] dimensions = new float[3];

    public Box(Node c, float w, float h, float d) {
        center = c;
        width = w;
        height = h;
        depth = d;

        dimensions[0] = width;
        dimensions[1] = height;
        dimensions[2] = depth;

        for (int i = 0; i < 3; i++) {
            bound[i] = c.coordinates[i] - dimensions[i] / 2.0f;
            bound[i + 3] = c.coordinates[i] + dimensions[i] / 2.0f;
        }
    }

    public boolean is_inside(Node p) {
        if (in_range(p.getX(), center.getX() - width / 2.0, center.getX() + width / 2.0)) {
            if (in_range(p.getY(), center.getY() - height / 2.0, center.getY() + height / 2.0)) {
                if (in_range(p.getZ(), center.getZ() - depth / 2.0, center.getX() + depth / 2.0)) {
                    return true;
                }
            }
        }
        return false;
    }


    public Node find_intersection(Node start, Node end) {
        return calculated_point_search(start, end);
    }

    public Node calculated_point_search(Node start, Node end) {

        ArrayList<Node> points_inside = new ArrayList<>();

        float x_step = end.getX() - start.getX();
        float y_step = end.getY() - start.getY();
        float z_step = end.getZ() - start.getZ();

        float[] steps = {x_step, y_step, z_step};

        for (int i = 0; i < bound.length; i++) {
            float line_plane_intersection_t = (bound[i] - start.coordinates[i % 3]) / steps[i % 3];
            float u_at_t = start.coordinates[(i + 1) % 3] + line_plane_intersection_t * steps[(i + 1) % 3];
            float v_at_t = start.coordinates[(i + 2) % 3] + line_plane_intersection_t * steps[(i + 2) % 3];
            if (in_range(u_at_t, bound[(i + 1) % 3], bound[(i + 1) % 3 + 3]) &&
                in_range(v_at_t, bound[(i + 2) % 3], bound[(i + 2) % 3 + 3])) {

                float[] p_inside_coord = new float[3];
                for (int j = 0; j < 3; j++) {
                    p_inside_coord[(i + j) % 3] = start.coordinates[(i + j) % 3] + line_plane_intersection_t * steps[(i + j) % 3];
                }
                points_inside.add(new Node(p_inside_coord[0], p_inside_coord[1], p_inside_coord[2]));

            }
        }

        if (points_inside.size() == 0) {
            return new Node(4.0f / 0.0f, 3.0f / 0.0f, 2.0f / 0.0f);
        }

        ArrayList<Double> distances = new ArrayList<Double>();
        for (int i = 0; i < points_inside.size(); i++) {
            distances.add(distance(start, points_inside.get(i)));
        }
        int min_index = minimum_index(distances);

        return points_inside.get(min_index);

    }

    public int minimum_index(ArrayList<Double> a) {
        double min = a.get(0);
        int index = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < min) {
                min = a.get(i);
                index = i;
            }
        }
        return index;
    }


}
