package kafka.simple1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-02-23
 */
public class ConsumerFastStart {
    public static final String brokerList = "8.140.123.226:9092";
    public static final String topic = "test";
    public static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id", groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        TopicPartition tp = new TopicPartition(topic, 0);
        consumer.assign(Arrays.asList(tp));
        //当前消费到的位移
        long lastConsumedOffset = -1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            if (records.isEmpty()) {
                break;
            }
            List<ConsumerRecord<String, String>> partitionRecords
                    = records.records(tp);
            lastConsumedOffset = partitionRecords
                    .get(partitionRecords.size() - 1).offset();
            consumer.commitSync();//同步提交消费位移
        }
        System.out.println("comsumed offset is " + lastConsumedOffset);
        OffsetAndMetadata offsetAndMetadata = consumer.committed(tp);
        System.out.println("commited offset is " + offsetAndMetadata.offset());
        long posititon = consumer.position(tp);
        System.out.println("the offset of the next record is " + posititon);
//        consumer.subscribe(Collections.singletonList(topic));
//        while (true) {
//            ConsumerRecords<String, String> records =
//                    consumer.poll(Duration.ofMillis(1000));
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println(record.value());
//            }
//        }
    }
}
