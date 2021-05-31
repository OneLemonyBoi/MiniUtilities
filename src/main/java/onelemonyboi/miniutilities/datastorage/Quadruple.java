package onelemonyboi.miniutilities.datastorage;

public class Quadruple<A, B, C, D> {

    public A one;
    public B two;
    public C three;
    public D four;

    public Quadruple(A one, B two, C three, D four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }

    public static <A, B, C, D> Quadruple<A, B, C, D> from(A one, B two, C three, D four) {
        return new Quadruple<>(one, two, three, four);
    }
}
