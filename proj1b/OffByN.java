public class OffByN implements CharacterComparator{

    private int n;

    public OffByN(int N) {
        n = N;
    }

    /**
     * Returns true for characters that are different by exactly N.
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(y - x);
        if(diff == n){
            return true;
        }
        return false;
    }

}
