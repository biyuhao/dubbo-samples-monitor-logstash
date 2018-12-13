/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.apache.dubbo.samples.monitor.logstash;

import org.apache.dubbo.samples.monitor.logstash.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class BasicConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-demo-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy

        new Thread(()->{
            Random random = new Random(System.currentTimeMillis());
            while (true) {
                try {
                    Thread.sleep(random.nextInt(5 * 1000));
                    String hello = demoService.sayHello("world"); // call remote method
                    System.out.println(hello); // get result
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            Random random = new Random(System.currentTimeMillis());
            while (true) {
                try {
                    Thread.sleep(random.nextInt(3 * 1000));
                    String goodbye = demoService.sayGoodbye("bye"); // call remote method
                    System.out.println(goodbye); // get result
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).start();

    }
}