import com.zk.junit.Calculator;
import org.junit.Test;

public class CalculatorTest {
    private Calculator cal = new Calculator();
    // 1. 与原方法保持一致
    // 2. 在原方法前增加test前缀
    @Test
    public void testAdd() {
        int result = cal.add(1, 2);
        System.out.println(result);
    }

    @Test
    public void testSubtract() {
        int result = cal.subtract(1, 2);
        System.out.println(result);
    }

    @Test
    public  void testMultiply() {
        int result = cal.multiply(1, 2);
        System.out.println(result);
    }

    @Test
    public void testDivide() {
        float result = cal.divide(1, 2);
        System.out.println(result);
    }

    @Test
    public void testDivide1() {
        float result = cal.divide(1, 0);
        System.out.println(result);
    }
}
