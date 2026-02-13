import ru.gr0946x.Polynomial;

void main() {
    Polynomial p = new Polynomial(1, 2, 3);
    Polynomial p2 = new Polynomial(1, -2, -3);
    Polynomial p3 = new Polynomial(1, 0, -3);
    Polynomial p4 = new Polynomial(0, 0, -3);
    Polynomial p5 = new Polynomial(0, -1, -3);
    System.out.println(p);
    System.out.println(p2);
    System.out.println(p3);
    System.out.println(p4);
    System.out.println(p5);
}