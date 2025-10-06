package net.javaguides.sys.design.messenger;

import java.util.List;

public class MessengerMain {

    public static void main(String[] args) {
        MessengerService messengerService = new MessengerService();

        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        messengerService.addUser(user1);
        messengerService.addUser(user2);

        messengerService.sendMessage("1", "2", "Hello, Bob!");

        List<Message> user1Messages = messengerService.getMessagesByUserId("1");
        for (Message message : user1Messages) {
            System.out.println("Sender: " + message.getSenderId());
            System.out.println("Receiver: " + message.getReceiverId());
            System.out.println("Content: " + message.getContent());
            System.out.println();
        }
    }


}
