package common.configuration;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public final class TManConfiguration {

    private final long period;
    private final long seed;
    private final double temperature;
    private final int viewSize;
    private final int exchangeMsgSize;
    private final int msgTimeout;
    private final int numPartitions;
    
//-------------------------------------------------------------------
    public TManConfiguration(long seed, long period, double temperature, 
            int viewSize, int exchangeMsgSize, int msgTimeout, int numPartitions) {
        super();
        this.seed = seed;
        this.period = period;
        this.temperature = temperature;
        this.viewSize = viewSize;
        this.exchangeMsgSize = exchangeMsgSize;
        this.msgTimeout = msgTimeout;
        this.numPartitions = numPartitions;
        
    }

    public long getSeed() {
        return seed;
    }

//-------------------------------------------------------------------
    public long getPeriod() {
        return this.period;
    }

    //-------------------------------------------------------------------
    public double getTemperature() {
        return temperature;
    }

    public int getViewSize() {
        return viewSize;
    }

    public int getExchangeMsgSize() {
        return exchangeMsgSize;
    }

    public int getMsgTimeout() {
        return msgTimeout;
    }

    public int getNumPartitions() {
        return numPartitions;
    }
    
//-------------------------------------------------------------------
    public void store(String file) throws IOException {
        Properties p = new Properties();
        p.setProperty("seed", "" + seed);
        p.setProperty("period", "" + period);
        p.setProperty("temperature", "" + temperature);
        p.setProperty("viewSize", "" + viewSize);
        p.setProperty("exchangeMsgSize", "" + exchangeMsgSize);
        p.setProperty("msgTimeout", "" + msgTimeout);
        p.setProperty("numPartitions", "" + numPartitions);
        
        Writer writer = new FileWriter(file);
        p.store(writer, "se.sics.kompics.p2p.overlay.application");
    }

//-------------------------------------------------------------------
    public static TManConfiguration load(String file) throws IOException {
        Properties p = new Properties();
        Reader reader = new FileReader(file);
        p.load(reader);

        long seed = Long.parseLong(p.getProperty("seed"));
        long period = Long.parseLong(p.getProperty("period"));
        double temp = Double.parseDouble(p.getProperty("temperature"));
        int viewSize = Integer.parseInt(p.getProperty("viewSize"));
        int exchangeMsgSize = Integer.parseInt(p.getProperty("exchangeMsgSize"));
        int msgTimeout = Integer.parseInt(p.getProperty("msgTimeout"));
        int numPartitions = Integer.parseInt(p.getProperty("numPartitions"));
        
        return new TManConfiguration(seed, period, temp, viewSize, 
                exchangeMsgSize, msgTimeout, numPartitions);
    }
}
