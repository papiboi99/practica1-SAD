
import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    Line line;
    InputStreamReader in;
    
    public EditableBufferedReader(Reader reader) {
        super(reader);
    }
    
    public void setRaw(){
        try{
        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        }catch(Exception e){

        }
    }
    
    public void unsetRaw(){
        try{
        String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        }catch(Exception e){

        }
    }
    
    @Override
    public int read() throws IOException{
        line = new Line();
	int in;
        char ch;
        this.setRaw();
        
        
        while (true) {
            in = super.read();
	    switch (in) {
	        case 13 :
		    return 0;
		case 279167 :
		    line.right();
		case 279168 :
		    line.left();
		case 279151126 :
		    line.del();
		case 127 :
		    line.bksp();
		case 279150126 :
		    line.ins();
		case 279170 :
		    line.end();
		case 279172 :
		    line.home();
	    }
	    
	    //ch = (char) in;
            //line.setStr(Character.toString(ch));
	    line.setStr(Integer.toString(in));
        }
    }
    
    @Override
    public String readLine()throws IOException{
        int i = read();
	this.unsetRaw();
        return line.getLine();
    }
    
    
}
