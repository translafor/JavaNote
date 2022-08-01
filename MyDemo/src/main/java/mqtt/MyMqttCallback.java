package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @description: 客户端回调函数
 * @author: frank.wu
 * @create: 2021-09-07
 */
public class MyMqttCallback implements MqttCallback {
    //端看连接之后被调用
    @Override
    public void connectionLost(Throwable arg0) {
        System.out.println("Connection Lost:"+arg0.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(String.format("get Msg: %s from Topic: %s", mqttMessage, s));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        if(iMqttDeliveryToken.isComplete()){
            System.out.println(String.format("Delivery a Msg to Topic: %s",iMqttDeliveryToken.getTopics()[0]));
        }
    }



}
