/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

/**
 *
 * @author Rafaela
 */
public class Data {
    
	private long RoundTripTime;
        private long time;
	private int Nconnection;
        private int Loss;
        private int Nregistered;
        private int Total;
	
	
	
	
	public Data() {
		this.RoundTripTime = 0;
		this.Nregistered = 0;
		this.Loss = 0;
		this.Nconnection = 0;
		this.Total = 0;
	}

    public long getRoundTripTime() {
        return RoundTripTime;
    }

    public void setRoundTripTime(long RoundTripTime) {
        this.RoundTripTime = RoundTripTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long Time) {
        this.time = Time;
    }

    public int getNconnection() {
        return Nconnection;
    }

    public void setNconnection(int Nconnection) {
        this.Nconnection = Nconnection;
    }

    public int getLoss() {
        return Loss;
    }

    public void setLoss(int Loss) {
        this.Loss = Loss;
    }

    public int getNregistered() {
        return Nregistered;
    }

    public void setNregistered(int Nregistered) {
        this.Nregistered = Nregistered;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
	
	
    
    
}
