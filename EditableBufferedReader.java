package sad.practica1;

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
        char ch;
        this.setRaw();
        
        
        while (true) {
            ch = (char) super.read();
            line.setStr(Character.toString(ch));
            
            if (ch == '\n') {return 0;}
        }
    }
    
    @Override
    public String readLine()throws IOException{
        int i = read();
        return line.getLine();
    }
    
    
}
