package test;

import top.inewbie.mq.SubSelectionInsertConsumer;

public class ConsumeTest {

    public static void main(String[] args) {
        try {
            new SubSelectionInsertConsumer().consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
