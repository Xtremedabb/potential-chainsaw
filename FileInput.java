import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *  This class provides convenience methods
 *  for the reading of lines from space delimited files. If an error occurs,
 *  execution is aborted, <i>i.e.,</i> no exceptions are thrown which must be caught.
 */
public class FileInput {
  /**/
  private boolean eof;
  private BufferedReader br;
  private String l, nl, fn;
  /**
   *  Open the input file.
   *
   *  @param inputFileName The input file name.
   */
  public FileInput ( String inputFileName ) {
    /**/
    File f;
    /**/
    f=new File (inputFileName);
    br=null;
    try {
      br=new BufferedReader( new FileReader(f) );
    }
    catch ( FileNotFoundException fnfe ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("File \"" + inputFileName + "\" not found.");
      System.exit(0);
    }
    /**/
    fn=inputFileName;
    initLine();
    nl=null;
    eof=false;
  }
  /**
   *  Close the input file.
   */
  public void close () {
    /**/
    try { br.close(); }
    catch ( IOException ioe ) { }
    /**/
    return;
  }
  /**
   *  Returns whether or not end-of-file has been reached.
   *
   *  @return true if end-of-file has been reached, false otherwise.
   */
  public boolean eof () {
    return eof;
  }
  /**
   *  Read a line from the input file. Will return <code>null</code> if <code>boolean eof()</code> returns <code>true</code>.
   *
   *  @return The line read as a String. The line-feed character is not returned.
   */
  public String readLine () {
    /**/
    String rv;
    /**/
    try {
      nl=br.readLine();
    }
    catch ( IOException ioe ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("Error when reading a line from file \"" + fn + "\".");
      System.exit(0);
    }
    if ( nl == null ) eof=true;
    /**/
    rv=l;
    l=nl;
    /**/
    return rv;
  }
  /**
   *  Read a line consisting of a single <code>int</code> from the input file.
   *  If <code>boolean eof()</code> returns <code>true</code>, then this method will throw a <code>RuntimeException</code>.
   *
   *  @return The line read as an int.
   */
  public int readLineInt() {
    /**/
    int rv;
    String s;
    /**/
    s=readLine().trim();
    rv=0;
    try {
      rv=Integer.parseInt(s);
    }
    catch ( NumberFormatException nfe ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("\"" + s + "\" does not represent a valid int.");
      System.exit(0);
    }
    /**/
    return rv;
  }
  /**
   *  Read a line consisting of a single <code>double</code> from the input file.
   *  If <code>boolean eof()</code> returns <code>true</code>, then this method will throw a <code>RuntimeException</code>.
   *
   *  @return The line read as a double.
   */
  public double readLineDouble() {
    /**/
    double rv;
    String s;
    /**/
    s=readLine().trim();
    rv=0.0;
    try {
      rv=Double.parseDouble(s);
    }
    catch ( NumberFormatException nfe ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("\"" + s + "\" does not represent a valid double.");
      System.exit(0);
    }
    /**/
    return rv;
  }
  /**
   *  Read a line from the input file.
   *  If <code>boolean eof()</code> returns <code>true</code>, then this method will throw a <code>RuntimeException</code>.
   *
   *  @return The line read as an array of tokens.
   */
  public String [] readLineTokens () {
    /**/
    return tokens( readLine() );
  }
  /**
   *  Read a line from the input file.
   *  If <code>boolean eof()</code> returns <code>true</code>, then this method will throw a <code>RuntimeException</code>.
   *
   *  @return The line read as an array of ints.
   */
  public int [] readLineInts () {
    /**/
    int nt;
    int [] rv;
    String [] t;
    /**/
    t=tokens( readLine() );
    nt=t.length;
    rv=new int [nt];
    for ( int i=0; i<nt; ++i ) {
      rv[i]=0;
      try {
        rv[i]=Integer.parseInt(t[i]);
      }
      catch ( NumberFormatException nfe ) {
        System.out.println();
        System.out.println("FileInput:");
        System.out.println("\"" + t[i] + "\" does not represent a valid int.");
        System.exit(0);
      }
    }
    /**/
    return rv;
  }
  /**
   *  Read a line from the input file.
   *  If <code>boolean eof()</code> returns <code>true</code>, then this method will throw a <code>RuntimeException</code>.
   *
   *  @return The line read as an array of doubles.
   */
  public double [] readLineDoubles () {
    /**/
    int nt;
    double [] rv;
    String [] t;
    /**/
    t=tokens( readLine() );
    nt=t.length;
    rv=new double [nt];
    for ( int i=0; i<nt; ++i ) {
      rv[i]=0.0;
      try {
        rv[i]=Double.parseDouble(t[i]);
      }
      catch ( NumberFormatException nfe ) {
        System.out.println();
        System.out.println("FileInput:");
        System.out.println("\"" + t[i] + "\" does not represent a valid double.");
        System.exit(0);
      }
    }
    /**/
    return rv;
  }
  /**/
  private void initLine () {
    /**/
    try {
      l=br.readLine();
    }
    catch ( IOException ioe ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("Error when reading a line from file \"" + fn + "\".");
      System.exit(0);
    }
    if ( l == null ) {
      System.out.println();
      System.out.println("FileInput:");
      System.out.println("File \"" + fn + "\" is empty.");
      System.exit(0);
    }
    /**/
    return;
  }
  /**/
  private static String [] tokens ( String s ) {
    /**/
    int nnet;
    ArrayList<String> als;
    String [] t, rv;
    /**/
    als=new ArrayList<String> ();
    t=s.split(" ");
    for ( int i=0; i<t.length; ++i ) {
      if ( !t[i].equals("") ) als.add(t[i]);
    }
    nnet=als.size();
    rv=new String [nnet];
    for ( int i=0; i<nnet; ++i ) rv[i]=als.get(i);
    als=null;
    /**/
    return rv;
  }
}
