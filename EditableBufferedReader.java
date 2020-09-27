/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad.practica1;

import java.io.BufferedReader;
import java.io.Reader;

/**
 *
 * @author david.chicano
 */
public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(Reader reader) {
        super(reader);
    }
    
    public void setRaw(){
    
    }
    
    public void unsetRaw(){
    
    }
    
    @Override
    public int read(){
        return 0;
        
    }
    
    @Override
    public String readLine(){
        return null;
        
    }
    
    
}
