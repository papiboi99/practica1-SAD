import java.util.*;

public class Line {

//right, left: caràcter dreta, caràcter esquerra amb les fletxes.
//home, end: principi, final de línia.
//ins: commuta mode inserció/sobre-escriptura.
//del, bksp: esborra caràcter actual o caràcter a l’esquerra.
    
    static int home = 0;
    int cursor;
    int end;
    ArrayList<Character> ch;
    boolean ins;
    
    public Line (){
        cursor = 0;
        end = 0;
        ins = false;
        ch = new ArrayList<Character>();
    }
    
    public void keyPressed (int key){
        switch (key) {
            case 0: this.right();
                    break;
            case 1: this.left();
                    break;
            case 2: this.home();
                    break;
            case 3: this.end();
                    break;
            case 4: this.ins();
                    break;
            case 5: this.del();
                    break;
            case 127: this.bksp();
                      break;
            default: setStr((char) key);
        }
        
        this.shellRefresh();
    }
    
    public void setStr (char c){
        if (ins == true){
            ch.set(cursor,c);
            if(cursor <= end){
                end++;
            }
        }else{
            ch.add(cursor,c);
            end++;
        }
        cursor++;
        
        //Runtime.getRuntime().exec("echo \""+Character.toString(c)+"\"").waitFor();
    }
    
    public String getLine (){
        return Arrays.toString(ch.toArray());
    }
    
    public void right (){
        if (cursor < end) {
            cursor++;
        }
    }
    
    public void left (){
        if (cursor > 0) {
            cursor--;
        
        }
    }    
    
    public void del (){
        if (cursor < end) {
            ch.remove(cursor);
            end--;
        }
    }

    public void bksp (){
        if (cursor > 0) {
            ch.remove(cursor-1);
            end--;
            cursor--;
        }
    }

    public void ins (){
        ins = !ins;
    }

    public void home (){
        cursor = home;
    }

    public void end (){
        cursor = end;
    }
    
    public void shellRefresh(){
    	/*Eliminamos el contenido en la terminal para actualizarlo
        String[] cmd = {"/bin/sh", "-c", "stty -flusho </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor(); */
    }
    
}

