package ua.com.delydreamer.cryptos;

import android.util.Log;

public class CryptosJ
{
    public String alphabet_en = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String alphabet_ua = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";

    private String GetRepeatKey(String s, int n)
    {
        String p = s;
        while (p.length() < n)
        {
            p += p;
        }

        Log.d("delylog", "key:"+p.substring(0, n));

        return p.substring(0, n);
    }

    public String encryptVigenere(String text, String key, boolean key_minus)
    {
        Log.d("delylog", "vigenere1");
        String result = "";

        String gamma = GetRepeatKey(key, text.length());

        int i_k = 0; // key pos

        for (int i = 0; i < text.length(); i++)
        {
            Log.d("delylog", "vigenere2"+i);
            String alphabet = "";

            if (alphabet_en.contains(Character.toString(Character.toUpperCase(text.charAt(i)))))
            {
                alphabet = alphabet_en;
            }
            else
            {
                alphabet = alphabet_ua;
            }

            int a_length = alphabet.length();

            char ch = text.charAt(i);
            char ch_result = ch;

            boolean current_case = Character.isUpperCase(ch);

            ch = Character.toUpperCase(ch);

            String key_alphabet = alphabet;

            if (alphabet_en.contains(Character.toString(Character.toUpperCase(gamma.charAt(i_k)))))
            {
                key_alphabet = alphabet_en;
            }
            else
            {
                key_alphabet = alphabet_ua;
            }

            char k = gamma.charAt(i_k);
            k = Character.toUpperCase(k);

            if (alphabet.contains(Character.toString(ch)))
            {
                int ch_pos = alphabet.indexOf(ch);
                int key_pos = key_alphabet.indexOf(k);

                if (key_minus) key_pos = 0 - key_pos; // For decode

                int n = (a_length + ch_pos + key_pos) % a_length;

                Log.d("delylog","key_alphabet:"+key_alphabet);
                Log.d("delylog","main_alphabet:"+alphabet);
                Log.d("delylog", "n:"+n+" | key_pos:"+key_pos+" | ch_pos:"+ch_pos+" | a_length:"+a_length);

                ch_result = alphabet.charAt(n);

                Log.d("delylog", "vigenere6");

                if (!current_case) ch_result = Character.toLowerCase(ch_result);
                i_k++;
            }

            result += ch_result;
        }

        return result;
    }
}
