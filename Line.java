
public class Line {

//right, left: caràcter dreta, caràcter esquerra amb les fletxes.
//home, end: principi, final de línia.
//ins: commuta mode inserció/sobre-escriptura.
//del, bksp: esborra caràcter actual o caràcter a l’esquerra.
    
    static int home = 0;
    int cursor;
    int end;
    char[] ch;
    boolean ins;
    
    public Line (){
        cursor = 0;
        end = 0;
        ins = true;
    }
    
    public void keyPressed (int key){
        switch (key) {
            case 0: this.right();
            case 1: this.left();
            case 2: this.home();
            case 3: this.end();
            case 4: this.ins();
            case 5: this.del();
            case 127: this.bksp();
            default: setStr((char) key);
        }
    }
    
    public void setStr (char c){
        if (ins == true){
            
        }else{
            ch[cursor] = c;
        }
        cursor++;
    }
    
    public String getLine (){
        return str;
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
        
    }
    
    public void bksp (){
        
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
    
}

