package study.test1.concurrent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * akka
 *
 * @author chao
 * @since 2019-06-10
 */
public class AkkaTest extends UntypedActor{

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("helloSystem");
        ActorRef ref = system.actorOf(Props.create(AkkaTest.class));
        ref.tell("didi", ActorRef.noSender());
    }

    @Override
    public void onReceive(Object message) {
        System.out.println("hello " + message);
    }
}
