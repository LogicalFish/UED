/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

/**
 *
 * @author Niels Visser
 */
public class InstrumentHolder {
    
    private static InstrumentHolder instance = null;
    private List<GridConfiguration> configurations;
    
    private InstrumentHolder() {
        this.configurations = new ArrayList<GridConfiguration>();
    }
    
    public static InstrumentHolder getInstance() {
        if(instance == null) {
            instance = new InstrumentHolder();
        }
        return instance;
    }
    
    public static Instrument[] InstrumentList() {
        return Player.getInstance().getSynthesizer().getAvailableInstruments();
    }
    
    public static Instrument GetDrums() {
        return SearchInstrument("drum");
    }
    
    public static Instrument SearchInstrument(String partialName) {
        Instrument[] il = InstrumentList();
        for(Instrument i : il) {
            if(i.getName().toLowerCase().contains(partialName)) {
                return i;
            }
        }
        return null;
    }
    
    public void addConfiguration(GridConfiguration gc) {
        this.configurations.add(gc);
    }
    
    public GridConfiguration GetGridConfigurationByInstrument(Instrument i) {
        for(GridConfiguration gc : this.configurations) {
            if(gc.instrument == i) {
                return gc;
            }
        }
        return null;
    }
    
    public List<GridConfiguration> getAvailableConfigurations() {
        return this.configurations;
    }
}
