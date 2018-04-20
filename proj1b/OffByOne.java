public class OffByOne implements CharacterComparator {
    /**
     * Returns true for characters that are different by exactly one.
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(y - x);
        if(diff == 1){
            return true;
        }
        return false;
    }


}
