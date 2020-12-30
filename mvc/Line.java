package mvc;

import java.util.*;

public class Line {

//right, left: caràcter dreta, caràcter esquerra amb les fletxes.
//home, end: principi, final de línia.
//ins: commuta mode inserció/sobre-escriptura.
//del, bksp: esborra caràcter actual o caràcter a l’esquerra.
    int cursor;
    ArrayList<Character> ch;
    boolean ins;

    public Line() {
        cursor = 0;
        ins = false;
        ch = new ArrayList<Character>();
    }

    //Si ins == true --> false;
    //Si ins == false --> true;
    public boolean setStr(char c) {
        if (ins == true) {
            if (cursor >= ch.size() - 1) {
                ch.add(cursor, c);
            } else {
                ch.set(cursor, c);
            }
            cursor++;
            return true;
        } else {
            ch.add(cursor, c);
            cursor++;
            return false;
        }
    }

    public String getLine() {
        Character[] arr = new Character[ch.size()];
        arr = ch.toArray(arr);
        String str = "";
        for (Character c : arr) {
            str += c.toString();
        }
        return str;
    }

    public boolean right() {
        if (cursor < ch.size()) {
            cursor++;
            return true;
        } else {
            return false;
        }
    }

    public boolean left() {
        if (cursor > 0) {
            cursor--;
            return true;
        } else {
            return false;
        }
    }

    //Si no es posible hacer delete return false
    //Si es posible return true
    public boolean del() {
        if (cursor < ch.size()) {
            ch.remove(cursor);
            return true;
        } else {
            return false;
        }
    }

    //Si no es posible hacer backspace return false
    //Si es posible return true
    public boolean bksp() {
        if (cursor > 0) {
            ch.remove(cursor - 1);
            cursor--;
            return true;
        } else {
            return false;
        }
    }

    public void ins() {
        ins = !ins;
    }

    public int home() {
        int aux = cursor;
        cursor = 0;
        return aux;
    }

    public int end() {
        int aux = cursor;
        cursor = ch.size();
        return (ch.size() - aux);
    }

    public Character getNextChar(int i) {
        return ch.get(cursor + i);
    }

}

