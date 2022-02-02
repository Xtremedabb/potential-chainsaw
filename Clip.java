public class Clip {
  /**/
  public static void main ( String [] arg ) {
    /**/
    int [] signal, clippedSignal;
    /**/
    signal = read();
    clippedSignal = clip(signal,1000);
    print(clippedSignal);
    /**/
    return;
  }
  /*
   *  Input:    a = an int [] of non-negative integers representing a signal
   *          max = the maximum safe value of the signal
   *  Return: an int [] with entries clipped to obey the maximum safe value
   */
  public static int [] clip ( int [] a, int max ) {
    /*
     *  Put required code here
     */
  }
  /**/
  private static int [] read () {
    /**/
    int len;
    int [] rv;
    FileInput fi;
    /**/
    fi = new FileInput("signal.txt");
    len = fi.readLineInt();
    rv = new int [len];
    fi.readLine();
    for ( int i=0; i<len; ++i ) rv[i]=fi.readLineInt();
    fi.close();
    /**/
    return rv;
  }
  /**/
  private static void print ( int [] a ) {
    /**/
    int len;
    FileOutput fo;
    /**/
    len=a.length;
    fo=new FileOutput("clipped_signal.txt");
    fo.println(len);
    fo.println("/**/");
    for ( int i=0; i<len; ++i ) fo.println("%4d",a[i]);
    fo.close();
    /**/
    return;
  }
}