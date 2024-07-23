package codingon_kdt.spring_boot_default._02_dto_vo;

import codingon_kdt.spring_boot_default._02_dto_vo.vo.Point;

public class PointExample {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        System.out.println("p1 = " + p1);
        System.out.println("p1 = " + p1);

        System.out.println("Distance between p1 and p2 = " + p1.distanceTo(p2)); // 5.0

        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 4);

        System.out.println("Are p2 and p3 the same point? " + p2.equals(p3)); // true
        System.out.println("Are p2 and p4 the same point? " + p2.equals(p4)); // false

        // 해시 코드
        System.out.println("Hash code of p1: " + p1.hashCode()); // 961
        System.out.println("Hash code of p2: " + p2.hashCode()); // 1058 // 값이 같으면 같은 참조 값 가짐 -> vo 특징
        System.out.println("Hash code of p3: " + p3.hashCode()); // 1058
        System.out.println("Hash code of p4: " + p4.hashCode()); // 1089
    }
}
