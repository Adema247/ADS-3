import java.util.Random;

public class MyTestingClass {
    private int x;
    private int y;

    public MyTestingClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
}
