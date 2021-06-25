package Java-programs;

public class Anagram {
    void check(String str1, String str2) {
        char str1Copy[] = str1.toCharArray(), str2Copy[] = str2.toCharArray();
        if (str1.length() != str2.length()) {
            System.out.println("The words " + str1 + " and " + str2 + " are not anagrams!");
            System.exit(0);
        }
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1Copy[i] == '-' || str2Copy[j] == '-')
                    continue;
                if (str1Copy[i] == str2Copy[j]) {
                    str1Copy[i] = str2Copy[j] = '-';
                }
            }
        }
        if (String.valueOf(str1Copy).equals("----") && String.valueOf(str2Copy).equals("----")) 
            System.out.println("The words " + str1 + " and " + str2 + " are anagrams!");
        else
            System.out.println("The words " + str1 + " and " + str2 + " are not anagrams!");
    }
}
