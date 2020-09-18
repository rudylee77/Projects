public class OffByN implements CharacterComparator{
    public int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x-y == N) || (x-y == -N);
    }
}
