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
    
    //Si ins == true --> return numero de caracteres por delante de cursor;
    //Si ins == false --> return -1;
    public int setStr (char c){
        if (ins == true){
            if(cursor >= end){
                end++;
                ch.add(cursor,c);
            }else{
            	 ch.set(cursor,c);
            }
            cursor++;
            return -1;
        }else{
            ch.add(cursor,c);
            end++;
            cursor++;
            return (end-cursor);
        }
    }
    
    public String getLine (){
    	Character[] arr = new Character[ch.size()]; 
        arr = ch.toArray(arr);
        String str = "";
	for (Character c : arr)
            str += c.toString();
        return str;
    }
    
    public boolean right (){
        if (cursor < end) {
            cursor++;
            return true;
        }else{return false;}
    }
    
    public boolean left (){
        if (cursor > 0) {
            cursor--;
            return true;
        }else{return false;}
    }    
    
    //Si no es posible hacer delete return -1
    //Si es posible return numero de caracteres por delante de cursor
    public int del (){
        if (cursor < end) {
            ch.remove(cursor);
            end--;
            return (end-cursor);
        }else{return -1;}
    }

    //Si no es posible hacer delete return -1
    //Si es posible return numero de caracteres por delante de cursor
    public int bksp (){
        if (cursor > 0) {
            ch.remove(cursor-1);
            end--;
            cursor--;
            return (end-cursor);
        }else{return -1;}
    }

    public void ins (){
        ins = !ins;
    }

    public int home (){
        int aux = cursor;
        cursor = home;
        return aux;
    }

    public int end (){
        int aux = cursor;
        cursor = end;
        return (end-aux);
    }
    
    public Character getNextChar(int i){
    	return ch.get(cursor+i);
    }
    
}

