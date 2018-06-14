/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class NFA extends Automaton{
    
    StateNFA initial;
    ArrayList<State> last;
    
    NFA(){
        super();
        last = new ArrayList<>();
    }
    
    void addInitialState(StateNFA state){
        
        initial = state;
        addState(state);
    
    }
    
    @Override
    void addLastState(State state){
        
        last.add((StateNFA)state);
        addState((StateNFA)state);
    
    }
    
    DFA convertToAFD(){
        
        DFA dfa = new DFA();
        
        createStates(dfa);
            
        return dfa;
    }
    
    @Override
    public String toString(){
        
        String back = "";
        
        for(Object object: programFunction){
            
            StateNFA state = (StateNFA) object;
            
            for(Transition t: state.transitions){
                
                back += state.name + " " + t.letter + " " + t.next.name + "\n";
            
            }
        }
        
        return back;
    }
    
    private void createStates(DFA dfa){
        
        for(String letter: alphabet){     //For each letter in alphabet    
            
            statesForALetter(dfa, letter, "");  //Generate al the states posible for a letter
        
        }
    }
    
    private void statesForALetter(DFA dfa, String letter, String name){
        
        for(Object object: programFunction){
            
            StateNFA state = (StateNFA) object;
            
            for(Transition t: state.transitions){
                
                if(!t.alreadyPassed && t.letter.equals(letter)){ //If it didn't pass to this transition and the transiton letter is the same of the loop 
                    
                    t.alreadyPassed();                           //Sinalize that already passed
                    name += t.next.name;                         //Name of the new state
                    
                    if(!name.equals("")){                        //If name is different of the empty string (If has transition for this letter)
                        
                        State exist = dfa.findStateInProgramFunction(name);  
                        
                        if(exist == null){                       //If not exist a state with this name
                            
                            StateDFA newState = new StateDFA(name); //Create the new state
                            dfa.addState(newState);                 //Add the new state to DFA
                        
                        }
                    }
                }
            }
        }
    }
    
}
