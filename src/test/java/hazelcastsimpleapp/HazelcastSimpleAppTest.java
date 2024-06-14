package hazelcastsimpleapp;

import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HazelcastSimpleAppTest {

  HazelcastInstance createInstance() {
    HazelcastInstance instance = Hazelcast.newHazelcastInstance();
    MapConfig mapConfig = instance.getConfig().getMapConfig("map.name");
    mapConfig.setBackupCount(1);
    return instance;
  }

  @Test
  void test() {
    HazelcastInstance instance = createInstance();
    HazelcastInstance instance2 = createInstance();

    Map<Long, String> map = instance.getMap("a");
    FlakeIdGenerator gen = instance.getFlakeIdGenerator("gen");
    for (int i = 0; i < 10; i++) {
      map.put(gen.newId(), "stuff " + i);
    }

    Map<Long, String> map2 = instance2.getMap("a");
    for (Map.Entry<Long, String> entry : map2.entrySet()) {
      System.out.printf("entry: %d; %s\n", entry.getKey(), entry.getValue());
    }
  }

}