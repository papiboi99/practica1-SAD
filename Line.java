
package sad.practica1;

public class Line {

//right, left: caràcter dreta, caràcter esquerra amb les fletxes.
//home, end: principi, final de línia.
//ins: commuta mode inserció/sobre-escriptura.
//del, bksp: esborra caràcter actual o caràcter a l’esquerra.
    
    static int home = 0;
    int cursor;
    int end;
    String str;
    boolean ins;
    
    public Line (){
        cursor = 0;
        end = 0;
        str = null;
        ins = true;
    }
    
    public void setStr (String ch){
        str = str + ch;
    }
    
    public String getLine (){
        return str;
    }
    
    public void right (){
        
    }
    
    public void left (){
        
    }    
    
    public void del (){
        
    }
    
    public void bksp (){
        
    }
    
}
