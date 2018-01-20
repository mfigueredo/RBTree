public class RBElement {

    String key;
    String color;
    RBElement left;
    RBElement right;
    RBElement p;

    public RBElement()
    {
        key = "nil";
        left = null;
        right = null;
        color = "BLACK";
        p = null;
    }

}