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


You can draw many objects. The following is a cube with a sphere cut out of the center.

![Example5](https://github.com/mattBoros/Raytracer/blob/master/example5.png?raw=true)
![Example6](https://github.com/mattBoros/Raytracer/blob/master/example6.png?raw=true)
![Example7](https://github.com/mattBoros/Raytracer/blob/master/example7.png?raw=true)


You can even draw three dimensional Mandelbrot fractals.


![Example3](https://github.com/mattBoros/Raytracer/blob/master/example3.png?raw=true)


![Example4](https://github.com/mattBoros/Raytracer/blob/master/example4.png?raw=true)


