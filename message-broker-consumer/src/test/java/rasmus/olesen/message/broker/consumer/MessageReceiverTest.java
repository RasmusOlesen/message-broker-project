package rasmus.olesen.message.broker.consumer;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageReceiverTest {

    @Test
    public void testIsNotExpired() {
        //This should be done with a proper reference value. But since expiration is 1 minute, it is fine for POC.
        assertFalse(MessageReceiver.isExpired(new Date()));
    }

    @Test
    public void testIsExpiredToOld() {
        assertTrue(MessageReceiver.isExpired(new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1))));
    }

    @Test
    public void testIsExpiredToNew() {
        assertTrue(MessageReceiver.isExpired(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1))));
    }

    @Test
    public void testIsEven() {
        assertTrue(MessageReceiver.isEven(new Date(TimeUnit.SECONDS.toMillis(0))));
        assertTrue(MessageReceiver.isEven(new Date(TimeUnit.SECONDS.toMillis(2))));
        assertTrue(MessageReceiver.isEven(new Date(999)));
    }

    @Test
    public void testIsEvenNot() {
        assertFalse(MessageReceiver.isEven(new Date(TimeUnit.SECONDS.toMillis(1))));
        assertFalse(MessageReceiver.isEven(new Date(1999)));
    }

}