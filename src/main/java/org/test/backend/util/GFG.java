package org.test.backend.util;

import java.util.Random;

public class GFG {
    static String getAlphaNumericString(int n)
    {  // нижний предел для строчных букв
        int lowerLimit = 97;
        // нижний предел для строчных букв
        int upperLimit = 122;
        Random random = new Random();
        // Создать StringBuffer для сохранения результата
        StringBuffer r = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            // принимаем случайное значение от 97 до 122
            int nextRandomChar = lowerLimit + (int)(random.nextFloat() * (upperLimit - lowerLimit + 1));
            // добавить символ в конце bs
            r.append((char)nextRandomChar);
        }
        // возвращаем результирующую строку
        return r.toString();
    }
    public static void main(String[] args)
    {   // размер случайной буквенно-цифровой строки
        int n = 20;
        // Получить и отобразить буквенно-цифровую строку
        System.out.println(getAlphaNumericString(n));
    }
}
