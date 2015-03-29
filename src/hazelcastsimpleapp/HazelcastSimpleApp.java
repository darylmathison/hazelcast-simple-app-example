/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hazelcastsimpleapp;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;
import java.util.Map;

/**
 *
 * @author Daryl
 */
public class HazelcastSimpleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        HazelcastInstance instance2 = Hazelcast.newHazelcastInstance();
        
        Map<Long, String> map = instance.getMap("a");
        IdGenerator gen = instance.getIdGenerator("gen");
        for(int i = 0; i < 10; i++) {
            map.put(gen.newId(), "stuff " + i);
        }
        
        Map<Long, String> map2 = instance2.getMap("a");
        for(Map.Entry<Long, String> entry: map2.entrySet()) {
            System.out.printf("entry: %d; %s\n", entry.getKey(), entry.getValue());
        }
        
        System.exit(0);
    }
    
}
