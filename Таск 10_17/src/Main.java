import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите три пары координат через пробел");
        Scanner scanner = new Scanner(System.in);
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        System.out.println(answer(x1, y1, x2, y2, x3, y3));
    }

    public static int determinant(double x, double y) { // определение четверти
        if (x >= 0 && y >= 0) return 1;
        else if (x <= 0 && y >= 0) return 2;
        else if (x <= 0 && y <= 0) return 3;
        else return 4;
    }

    public static String answer(double x1, double y1, double x2, double y2, double x3, double y3) { // формирование ответа
        if (determinant(x1, y1) == determinant(x2, y2) && determinant(x2, y2) == determinant(x3, y3))
            return Integer.toString(determinant(x1, y1));
        if ((determinant(x1, y1) == determinant(x2, y2)
                && Math.abs(determinant(x1, y1) - determinant(x3, y3)) == 1)
                || (determinant(x2, y2) == determinant(x3, y3)
                && Math.abs(determinant(x1, y1) - determinant(x3, y3)) == 1))
            return Math.min(determinant(x1, y1), determinant(x3, y3))
                    + ", "
                    + Math.max(determinant(x1, y1), determinant(x3, y3));
        if (determinant(x1, y1) == determinant(x3, y3)
                && Math.abs(determinant(x2, y2) - determinant(x3, y3)) == 1)
            return Math.min(determinant(x2, y2), determinant(x3, y3)) + ", " + Math.max(determinant(x2, y2), determinant(x3, y3));
        if ((determinant(x1, y1) == determinant(x2, y2) && Math.abs(determinant(x1, y1) - determinant(x3, y3)) == 2) || (determinant(x2, y2) == determinant(x3, y3) && Math.abs(determinant(x1, y1) - determinant(x3, y3)) == 2) || (determinant(x1, y1) == determinant(x3, y3) && Math.abs(determinant(x2, y2) - determinant(x3, y3)) == 2)) {
            if (determinant(x1, y1) == determinant(x2, y2)) {
                if (isIntersection(x1, y1, x3, y3) && isIntersection(x2, y2, x3, y3) && isPlus(x1, y1, x3, y3) != isPlus(x2, y2, x3, y3))
                    return "Треугольник лежит во всех четвертях";
                else return outForTriple(determinant(x1, y1), determinant(x3, y3),x1,y1,x3,y3);
            } else if (determinant(x2, y2) == determinant(x3, y3)) {
                if (isIntersection(x2, y2, x1, y1) && isIntersection(x3, y3, x1, y1) && isPlus(x2, y2, x1, y1) != isPlus(x3, y3, x1, y1))
                    return "Треугольник лежит во всех четвертях";
                else return outForTriple(determinant(x1, y1), determinant(x3, y3),x1,y1,x3,y3);
            } else if (determinant(x1, y1) == determinant(x3, y3)) {
                if (isIntersection(x1, y1, x2, y2) && isIntersection(x3, y3, x2, y2) && isPlus(x1, y1, x2, y2) != isPlus(x3, y3, x2, y2))
                    return "Треугольник лежит во всех четвертях";
                else return outForTriple(determinant(x2, y2), determinant(x3, y3),x2,y2,x3,y3);
            }
        }
        if (determinant(x1, y1) != determinant(x2, y2) && determinant(x2, y2) != determinant(x3, y3) && determinant(x1, y1) != determinant(x3, y3)) {
            if (isIntersection(x1, y1, x2, y2) && isIntersection(x1, y1, x3, y3) && isPlus(x1, y1, x2, y2) == isPlus(x1, y1, x3, y3))
                return triple(determinant(x1, y1), determinant(x2, y2), determinant(x3, y3));
            if (isIntersection(x1, y1, x2, y2) && isIntersection(x2, y2, x3, y3) && isPlus(x1, y1, x2, y2) == isPlus(x2, y2, x3, y3))
                return triple(determinant(x1, y1), determinant(x2, y2), determinant(x3, y3));
            if (isIntersection(x1, y1, x3, y3) && isIntersection(x2, y2, x3, y3) && isPlus(x1, y1, x3, y3) == isPlus(x2, y2, x3, y3))
                return triple(determinant(x1, y1), determinant(x2, y2), determinant(x3, y3));
            return "Треугольник лежит во всех четвертях";
        }
        return triple(determinant(x1, y1), determinant(x2, y2), determinant(x3, y3));
    }

    public static boolean isIntersection(double x1, double y1, double x2, double y2) { // проверка на нахождение пересечения с y0 в промежутке x1 - x2
        double k = (y2 - y1) / (x2 - x1); //
        double c = y1 - k * x1;
        double x0 = -c / k;
        return (x0 <= Math.max(x1, x2) && x0 >= Math.min(x1, x2));
    }

    public static String triple(int a, int b, int c) { // вывод для 3 при возможности повтора
        int sr = a + b + c - Math.min(Math.min(a, b), c) - Math.max(Math.max(a, b), c);
        if (sr == Math.max(Math.max(a, b), c)) return Math.min(Math.min(a, b), c) + ", " + sr;
        if (sr == Math.min(Math.min(a, b), c)) return sr + ", " + Math.max(Math.max(a, b), c);
        return Math.min(Math.min(a, b), c) + ", " + sr + ", " + Math.max(Math.max(a, b), c);
    }

    public static boolean isPlus(double x1, double y1, double x2, double y2) {//проверка нахождения пересечения с y0
        double k = (y2 - y1) / (x2 - x1); //
        double c = y1 - k * x1;
        double x0 = -c / k;
        return x0 > 0;
    }

    public static String outForTriple(int a, int b, double x1, double y1, double x2, double y2) { // вывод 3 при условии разности четвертей в 2
        double k = (y2 - y1) / (x2 - x1);
        double c = y1 - k * x1;
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if (max == 4 && min == 2 && c > 0) return "1, 2, 4";
        if (max == 4 && min == 2 && c < 0) return "2, 3, 4";
        return min + ", " + (min + 1) + ", " + max;
    }
}
