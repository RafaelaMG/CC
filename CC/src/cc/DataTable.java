/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Rafaela
 */
public class DataTable {

    //Map with servers listed by IP
    private HashMap<String, Data> servers;

    public DataTable() {
        this.servers = new HashMap<>();
    }
   
    //add to server list

    public void addToServer(String IP) {
        if (!servers.containsKey(IP)) {
            System.out.println("New Server at: " + IP);
            this.servers.put(IP, new Data());
        }
    }

    //update time 
    public void updateTime(String IP, long t) {
        Data aux = servers.get(IP);
        aux.setTotal(aux.getTotal() + 1);
        aux.setTime(t);

    }

    public void updateList(long rtt, int connection, String IP) {
        Data aux = servers.get(IP);
        aux.setRoundTripTime(rtt);
        aux.setNconnection(connection);
        aux.setNregistered(aux.getNregistered() + 1);
        aux.setLoss(aux.getTotal() - aux.getNregistered());

    }

    // Selects best option for server
    public String selection() {
        String ret = new String();
        float rt = 90000000;
        int con = 15;

        //for each to get Strings from servers
        for (String s : this.servers.keySet()) {
            Data dt = servers.get(s);

            if (dt.getRoundTripTime() < rt) {
                if (dt.getNconnection() < con) {
                    ret = s;
                    rt = dt.getRoundTripTime();
                    con = dt.getNconnection();
                }
            } else if (dt.getNconnection() < con) {
                ret = s;
                rt = dt.getRoundTripTime();
                con = dt.getNconnection();
            }
            else if(dt.getLoss()<0.02 && dt.getNconnection()==0){
                ret= s;
                rt=dt.getRoundTripTime();
                con=dt.getNconnection();
                
            }
        }

        return ret;
    }

    public List<String> getServers() {

        List<String> ret = new ArrayList<>();
        servers.keySet().stream().forEach((s) -> {
            ret.add(s);
        });
        return ret;
    }

    public long getTime(String IP) throws NullPointerException {

        Data aux = servers.get(IP);
        return aux.getTime();

    }

    public void clean() {
        this.servers = new HashMap<>();
    }

}
