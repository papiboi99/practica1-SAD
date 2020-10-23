
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
        int numPos;
        while ((in = this.read()) != 13) {
            
            switch (in) {
                case 0: if (line.right() == true) {System.out.print("\033[C");}
                        break;
                case 1: if (line.left() == true) {System.out.print("\033[D");}
                        break;
                case 2: numPosH = line.home();
                	 System.out.print("\033["+numPosH+"D");
                        break;
                case 3: numPosE = line.end();
                	 System.out.print("\033["+numPosE+"C");
                        break;
                case 4: line.ins();
                        break;
                case 5: if ((numPos = line.del()) != -1) {
                            for (int i = 0; i < numPos; i++){
                                System.out.print(line.getNextChar(i));
                            }
                            numPos++;
                            System.out.print(" \033["+numPos+"D");
                        }
                        break;
                case 127: if ((numPos = line.bksp()) != -1) {
                	       System.out.print("\033[D");
                              for (int i = 0; i < numPos; i++){
                                  System.out.print(line.getNextChar(i));
                              }
                              numPos++;
                              System.out.print(" \033["+numPos+"D");
                          }
                          break;
                default: if ((numPos = line.setStr((char) in)) != -1) {
                             System.out.print("\033[C");
                	      for (int i = 0; i < numPos; i++){
                	          System.out.print(line.getNextChar(i));
                	      }
                	      numPos++;
                	      System.out.print("\033["+numPos+"D");
                	      System.out.print((char) in);
                	  }else {System.out.print((char) in);}
            }
        }

        this.unsetRaw();
        return line.getLine();
    }

}

