package mvc;

import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int HOME = 2;
    public static final int END = 3;
    public static final int INS = 4;
    public static final int DEL = 5;
    
    private Line line;
    private Console console;
    private InputStreamReader in;

    public EditableBufferedReader(Reader reader) {
        super(reader);
        line = new Line();
        console = new Console(line);
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

        int in;
        while ((in = this.read()) != 13) {
            console.output(in);
        }

        this.unsetRaw();
        return line.getLine();
    }

}

