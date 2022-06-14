package com.cracker.elasticjob;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

public class MyJobDemo2 {

    public static void main(String[] args) {
        new ScheduleJobBootstrap(createRegistryCenter(),
                new MyJob(),
                createJobConfiguration()).schedule();
        //OneOffJobBootstrap jobBootstrap = new OneOffJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration());
        //jobBootstrap.execute();
        //jobBootstrap.execute();
        //jobBootstrap.execute();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter registryCenter =
                new ZookeeperRegistryCenter(new ZookeeperConfiguration("127.0.0.1:2181", "my-job"));
        registryCenter.init();
        return registryCenter;
    }

    private static JobConfiguration createJobConfiguration() {
        return JobConfiguration.newBuilder("MyJob", 3)
                .cron("0/5 * * * * ?")
                //.shardingItemParameters("0=Beijing,1=Shanghai")
                .build();
    }
}
