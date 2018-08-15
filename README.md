# Raytracer
A Java program to render 3D objects which are mathematically defined. Makes it easy to create a new object and display it with a few lines of code.

# Example
The following code snippet results in a raytraced sphere with a cube cut out of the center.

```Python
Renderer renderer = new Renderer(new MultiObject()
                                 .add_object(new Sphere(origin, 1.5f), true)
                                 .add_object(new Box(origin, 4f, 1.5f, 1.5f), false));
renderer.display();
```

![Example](https://github.com/mattBoros/Raytracer/blob/master/example.png?raw=true)


![Example2](https://github.com/mattBoros/Raytracer/blob/master/example2.png?raw=true)
