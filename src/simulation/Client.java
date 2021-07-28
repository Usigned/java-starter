package simulation;

import java.util.ArrayList;
import java.util.List;

public class Client {

    static  class ZoomInVisitor implements Visitor {
        @Override
        public void visit(Rectangle rectangle) {
            rectangle.width *= 2;
            rectangle.height *= 2;
        }

        @Override
        public void visit(Circle circle) {
            circle.r *= 2;
        }
    }

    public static void main(String[] args) {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Rectangle(1, 2));
        shapeList.add(new Circle(10));

        shapeList.forEach(Shape::draw);

        for (Shape shape : shapeList) {
        /*     编译器无法推测shape的具体类型而调用相应的方法
             java是动态单绑定的语言
            Client.zoomIn(shape);
            故需要使用visitor模式
        */
            shape.setVisitor(new ZoomInVisitor());
        }

        shapeList.forEach(Shape::draw);
    }
}
