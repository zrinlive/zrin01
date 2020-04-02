package com.xproject.search;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Matcher {
    private static Matcher instance = new Matcher();

    static Matcher getInstance() {
        return instance;
    }

    private Matcher() {}

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     */
    void match(File file, String keyword) throws IOException
    {
        try (Scanner in = new Scanner(file))
        {
            int lineNumber = 0;
            while (in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
            }
        }
    }
}
