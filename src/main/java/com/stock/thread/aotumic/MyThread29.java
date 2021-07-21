package com.stock.thread.aotumic;

public class MyThread29 extends Thread{

    private ThreadDomain29 td;

    public MyThread29(ThreadDomain29 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.addNum();
    }

    public static void main(String[] args)
    {
        try
        {
            ThreadDomain29 td = new ThreadDomain29();
            MyThread29[] mt = new MyThread29[5];
            for (int i = 0; i < mt.length; i++)
            {
                mt[i] = new MyThread29(td);
            }
            for (int i = 0; i < mt.length; i++)
            {
                mt[i].start();
            }
            Thread.sleep(1000);
            System.out.println(ThreadDomain29.aiRef.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
