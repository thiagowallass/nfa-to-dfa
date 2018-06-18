/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

/**
 *
 * @author thiag
 */
public class Transition {
    String letter;
    State next; 
    
    Transition(String letter, State next){
        
        this.letter = letter;
        this.next = next;
        
    }
    
}
