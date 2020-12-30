package mvc;

import java.io.*;

public class Console {

    private Line line;

    private int numPosH;
    private int numPosE;

    public Console(Line line){
        this.line = line;
    }

    public void output(int in){
        switch (in) {
            case EditableBufferedReader.RIGHT:
                if (line.right()) {
                    System.out.print("\033[C");
                }
                break;
            case EditableBufferedReader.LEFT:
                if (line.left()) {
                    System.out.print("\033[D");
                }
                break;
            case EditableBufferedReader.HOME:
                numPosH = line.home();
                System.out.print("\033[" + numPosH + "D");
                break;
            case EditableBufferedReader.END:
                numPosE = line.end();
                System.out.print("\033[" + numPosE + "C");
                break;
            case EditableBufferedReader.INS:
                line.ins();
                break;
            case EditableBufferedReader.DEL:
                if (line.del()){
                    System.out.print("\033[P");
                }
                break;
            case 127:
                if (line.bksp()){
                    System.out.print("\033[D");
                    System.out.print("\033[P");
                }
                break;
            default:
                if (line.setStr((char) in)){
                    System.out.print((char) in);
                }else {
                    System.out.print("\033[@");
                    System.out.print((char) in);
                }
        }
    }
}


