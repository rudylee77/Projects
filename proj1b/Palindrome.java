public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> char1 = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            char1.addLast(word.charAt(i));
        }
        return char1;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        else if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque d = wordToDeque(word);
        String first = "";
        String last = "";
        for (int i = 0; i < word.length()/2; i++) {
            first += d.removeFirst();
            last += d.removeLast();
            if (!(first.equals(last))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (cc == null) {
            return isPalindrome(word);
        }
        else if (word == null) {
            return false;
        }
        else if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        CharacterComparator offByOne = new OffByOne();
        for (int i = 0; i < word.length()/2; i++) {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if (!(offByOne.equalChars(first, last))) {
                return false;
            }
        }
        return true;
    }
}
