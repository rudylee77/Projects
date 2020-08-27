public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int count = 0;
        while (x <= 45) {
            System.out.print(x + " ");
            count += 1;
            x += count;
        }
    }
}