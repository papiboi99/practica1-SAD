
import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    InputStreamReader in;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int HOME = 2;
    public static final int END = 3;
    public static final int INS = 4;
    public static final int DEL = 5;

    public EditableBufferedReader(Reader reader) {
        super(reader);
    }

    public void setRaw() {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();

        } catch (Exception e) {

        }
    }

    public void unsetRaw() {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();

        } catch (Exception e) {

        }
    }

    /*Este metodo solo hay que añadir la funcionalidad de poder
    identificar las teclas claves:
    	ESC[C - Move cursor right
    	ESC[D - Move cursor left
    	ESC[H - home
    	ESC[F - end
    	ESC[2~ - ins
    	ESC[3~ - del
     */
    @Override
    public int read() throws IOException {
        int in;

        switch (in = super.read()) {
            case '\033':
                super.read();
                switch (in = super.read()) {
                    case 'C':
                        return RIGHT;
                    case 'D':
                        return LEFT;
                    case 'H':
                        return HOME;
                    case 'F':
                        return END;
                    case '2':
                        super.read();
                        return INS;
                    case '3':
                        super.read();
                        return DEL;
                }
            default:
                return in;
        }
    }

    //Para salir del editor la tecla es CR (Carriage Return o Intro) y el 127 backspace
    @Override
    public String readLine() throws IOException {
        this.setRaw();
        Line line = new Line();

        int in;
        int numPosH;
        int numPosE;
        while ((in = this.read()) != 13) {

            switch (in) {
                case RIGHT:
                    if (line.right()) {
                        System.out.print("\033[C");
                    }
                    break;
                case LEFT:
                    if (line.left()) {
                        System.out.print("\033[D");
                    }
                    break;
                case HOME:
                    numPosH = line.home();
                    System.out.print("\033[" + numPosH + "D");
                    break;
                case END:
                    numPosE = line.end();
                    System.out.print("\033[" + numPosE + "C");
                    break;
                case INS:
                    line.ins();
                    break;
                case DEL:
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

        this.unsetRaw();
        return line.getLine();
    }

}

