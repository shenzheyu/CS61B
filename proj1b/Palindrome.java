public class Palindrome {

    /**
     * Converts a String to LinkedListDeque containing each character.
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * Judge whether a String is palindrome.
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        while(deque.size() > 1) {
            if(deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Judge whether a String is palindrome by a third method.
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        while(deque.size() > 1) {
            if(!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }



}
