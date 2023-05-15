// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import java.util.Random;

public class RandomString
{
    public static final char[] alphabet;
    public static final int alphabetLength;
    
    public static String nextString(final int lenght, final Random random) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; ++i) {
            sb.append(RandomString.alphabet[random.nextInt(RandomString.alphabet.length)]);
        }
        return sb.toString();
    }
    
    public static String nextString(final int lenght) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; ++i) {
            sb.append(RandomString.alphabet[random.nextInt(RandomString.alphabet.length)]);
        }
        return sb.toString();
    }
    
    public static String nextString(final String alphabet, final int lenght, final Random random) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; ++i) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
    
    public static String nextString(final String alphabet, final int lenght) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; ++i) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
    
    static {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        alphabetLength = RandomString.alphabet.length;
    }
}
