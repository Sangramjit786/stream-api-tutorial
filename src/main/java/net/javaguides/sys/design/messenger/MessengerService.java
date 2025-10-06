package net.javaguides.sys.design.messenger;

import java.util.*;

public class MessengerService {

    private Map<String, User> users;
    private List<Message> messages;

    public MessengerService() {
        this.users = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        User sender = getUserById(senderId);
        User receiver = getUserById(receiverId);

        if (sender == null || receiver == null) {
            System.out.println("User not found");
            return;
        }

        String messageId = UUID.randomUUID().toString();
        Message message = new Message(messageId, senderId, receiverId, content);
        messages.add(message);

        // Additional logic to handle real-time communication and notifications
        // ...
    }

    public List<Message> getMessagesByUserId(String userId) {
        List<Message> userMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getSenderId().equals(userId) || message.getReceiverId().equals(userId)) {
                userMessages.add(message);
            }
        }

        return userMessages;
    }

    // Additional methods for managing conversations, notifications, etc.
    // ...
}
