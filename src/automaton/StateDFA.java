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
public class StateDFA extends State{
    Transition transition;
    
    StateDFA(String name, Transition t){
        
        super(name);
        transition = t;
    
    }
    
    StateDFA(String name){
        
        super(name);
    
    }
    
    void setTransition(Transition t){
        
        transition = t;
    
    }
}
