
import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    InputStreamReader in;

    enum Key{
        RIGHT,
        LEFT,
        HOME,
        END,
        INS,
        DEL
    }
    
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
                    case 'C': return Key.RIGHT.ordinal();
                    case 'D': return Key.LEFT.ordinal();
                    case 'H': return Key.HOME.ordinal();
                    case 'F': return Key.END.ordinal();
                    case '2': 
                    	super.read();
                    	return Key.INS.ordinal();
                    case '3': 
                    	super.read();
                    	return Key.DEL.ordinal();
                }
                break;
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
        while ((in = this.read()) != 13) {
            line.keyPressed(in);
        }

        this.unsetRaw();
        return line.getLine();
    }

}

