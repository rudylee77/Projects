import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN = new OffByN(5);

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
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome(null));

        assertTrue(palindrome.isPalindrome("o"));

        assertFalse(palindrome.isPalindrome("persiflage"));

        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testisPalindromeOffbyOne() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));

        assertTrue(palindrome.isPalindrome("binding", offByN));

        assertTrue(palindrome.isPalindrome("o", offByOne));

        assertFalse(palindrome.isPalindrome("racecar", offByOne));

        assertFalse(palindrome.isPalindrome(null, offByOne));

        assertFalse(palindrome.isPalindrome("persiflage", null));
    }
}
