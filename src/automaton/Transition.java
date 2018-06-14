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
    boolean alreadyPassed; 
    
    Transition(String letter, State next){
        this.letter = letter;
        this.next = next;
        alreadyPassed = false;
    }
    
    Transition(String letter, String next){ //Caso o estado ainda não tenha sido criado
        this.letter = letter;
        this.next = new State(next);
        alreadyPassed = false;
    }
    
    void alreadyPassed(){
        if(!alreadyPassed)
            alreadyPassed = true;
    }
}
