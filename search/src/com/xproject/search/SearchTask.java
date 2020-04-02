package com.xproject.search;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * This task searches files for a given keyword.
 */
class SearchTask implements Runnable
{
    private BlockingQueue<File> queue;
    private String keyword;

    /**
     * Constructs a SearchTask.
     * @param queue the queue from which to take files
     * @param keyword the keyword to look for
     */
    SearchTask(BlockingQueue<File> queue, String keyword)
    {
        this.queue = queue;
        this.keyword = keyword;
    }

    public void run()
    {
        try
        {
            boolean done = false;
            while (!done)
            {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY)
                {
                    queue.put(file);
                    done = true;
                }
                else Matcher.getInstance().match(file, keyword);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
        }
    }
}
