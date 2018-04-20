import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome(new String("a")));
        assertTrue(palindrome.isPalindrome(new String("racecar")));
        assertTrue(palindrome.isPalindrome(new String("noon")));
        assertFalse(palindrome.isPalindrome(new String("horse")));
        assertFalse(palindrome.isPalindrome(new String("rancor")));
        assertFalse(palindrome.isPalindrome(new String("aaaaab")));
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(new String("a"),cc));
        assertTrue(palindrome.isPalindrome(new String("flake"),cc));
    }
}
